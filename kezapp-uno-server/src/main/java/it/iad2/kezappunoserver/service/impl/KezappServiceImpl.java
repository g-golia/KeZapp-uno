package it.iad2.kezappunoserver.service.impl;

import it.iad2.kezappunoserver.dto.InviaMessaggioDto;
import it.iad2.kezappunoserver.dto.RegistrazioneDto;
import it.iad2.kezappunoserver.dto.RichiediMessaggiDto;
import it.iad2.kezappunoserver.dto.RichiediRegistrazioneDto;
import it.iad2.kezappunoserver.model.Chat;
import it.iad2.kezappunoserver.model.Messaggio;
import it.iad2.kezappunoserver.repository.ChatRepository;
import it.iad2.kezappunoserver.repository.MessaggioRepository;
import it.iad2.kezappunoserver.service.KezappService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KezappServiceImpl implements KezappService {

    @Autowired
    ChatRepository chatRepository;
    @Autowired
    MessaggioRepository messaggioRepository;

    @Override
    public RegistrazioneDto registrazione(RichiediRegistrazioneDto dto) {
        RegistrazioneDto dtoR = new RegistrazioneDto();
        //verifico se esiste già il nickname nel repository chat
        Chat chat = chatRepository.findByNickname(dto.getNickname());
        // se nickname esiste
        if (chat != null) {
            dtoR.setSessione(chat.getSessione());
        // se nickname non esiste
        }else{
            Chat chatN = new Chat();
            chatN.setNickname(dto.getNickname());
            chatN = chatRepository.save(chatN);
            // salvataggio in repository per impostare sessione uguale a id
            String sessione = chatN.getId().toString(); //controllare se funziona
            chatN.setSessione(sessione);
            chatN = chatRepository.save(chatN);
            dtoR.setSessione(sessione);
        }
        return dtoR;
    }
    
    @Override
    public RegistrazioneDto inviaUno(InviaMessaggioDto dto) {
        RegistrazioneDto dtoR = new RegistrazioneDto();
        //cerca nel repository la chat in base alla sessione
        Chat chat = chatRepository.findBySessione(dto.getSessione());
        //se chat non esiste
        if (chat == null){
            return dtoR;
        }
        //se chat esiste
        else if (dto.getDestinatario() != null){
            Chat chatD = chatRepository.findByNickname(dto.getDestinatario());
            // se destinatario non esiste
            if (chatD == null){
                return dtoR;
            }
        }
        //crea e setta campi nuovo messaggio
        Messaggio messaggio = new Messaggio();
        messaggio.setAliasDestinatario(dto.getDestinatario());
        messaggio.setAliasMittente(chat.getNickname());
        messaggio.setTesto(dto.getMessaggio());
        messaggio = messaggioRepository.save(messaggio);
        
        return sincronizzaMsgContatti(chat);
    }
    
    @Override
    public RegistrazioneDto inviaTutti(InviaMessaggioDto dto) {
        dto.setDestinatario(null);
        return inviaUno(dto);
    }

    @Override
    public RegistrazioneDto aggiorna(RichiediMessaggiDto dto) {
        RegistrazioneDto dtoR = new RegistrazioneDto();
        //cerca nel repository la chat in base alla sessione
        Chat chat = chatRepository.findBySessione(dto.getSessione());
        //se chat non esiste
        if (chat == null){
            return dtoR;
        }
        //ritorno messaggi e contatti aggiornati
        return sincronizzaMsgContatti(chat);
    }
    
    
    private RegistrazioneDto sincronizzaMsgContatti(Chat chat){
        System.out.println("sono in sincronizzaMsgContatti");
        RegistrazioneDto dtoR = new RegistrazioneDto();
        //recupero delle chat , tranne quelle con il nickname della chat in ingresso
        List<Chat> contatti = chatRepository.findAll(); //proposta chatteChiatte
        List<Chat> listaAgg = contatti.stream().filter(c -> !(c.getNickname().equals(chat.getNickname()))).collect(Collectors.toList());
        //recupero tutti i messaggi con nickname e pubblici
        List<Messaggio> messaggi = messaggioRepository.findByAliasDestinatarioOrAliasDestinatario(chat.getNickname(), null);
        dtoR.setContatti(listaAgg);
        dtoR.setMessaggi(messaggi);
        return dtoR;
    }
    

    
    
    
    
}