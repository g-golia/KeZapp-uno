package it.iad2.kezappunoserver.service;

import it.iad2.kezappunoserver.dto.InviaMessaggioDto;
import it.iad2.kezappunoserver.dto.RegistrazioneDto;
import it.iad2.kezappunoserver.dto.RichiediMessaggiDto;
import it.iad2.kezappunoserver.dto.RichiediRegistrazioneDto;


public interface KezappService {

    RegistrazioneDto registrazione(RichiediRegistrazioneDto dto);

    RegistrazioneDto inviaTutti(InviaMessaggioDto dto);

    RegistrazioneDto aggiorna(RichiediMessaggiDto dto);

    RegistrazioneDto inviaUno(InviaMessaggioDto dto);

}
