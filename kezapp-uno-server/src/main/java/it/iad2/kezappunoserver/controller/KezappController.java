package it.iad2.kezappunoserver.controller;

import it.iad2.kezappunoserver.dto.InviaMessaggioDto;
import it.iad2.kezappunoserver.dto.RegistrazioneDto;
import it.iad2.kezappunoserver.dto.RichiediMessaggiDto;
import it.iad2.kezappunoserver.dto.RichiediRegistrazioneDto;
import it.iad2.kezappunoserver.service.KezappService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

public class KezappController {

    @CrossOrigin("*")
    @RestController
    public class KezappController00 {

        @Autowired
        KezappService kezappService;

        @RequestMapping(value = "/registrazione")
        @ResponseBody
        public RegistrazioneDto registrazione(@RequestBody RichiediRegistrazioneDto dto) {
            System.out.println("Siamo in registrazione!");

            return kezappService.registrazione(dto);
        }

        @RequestMapping(value = "/invia-uno")
        @ResponseBody
        public RegistrazioneDto inviaUno(@RequestBody InviaMessaggioDto dto) {
            System.out.println("Siamo in invia-uno!");

            return kezappService.inviaUno(dto);
        }

        @RequestMapping(value = "/invia-tutti")
        public RegistrazioneDto inviaTutti(@RequestBody InviaMessaggioDto dto) {
            System.out.println("Siamo in invia-tutti!");

            return kezappService.inviaTutti(dto);
        }

        @RequestMapping(value = "/aggiorna")
        public RegistrazioneDto aggiorna(@RequestBody RichiediMessaggiDto dto) {
            System.out.println("Siamo in aggiorna!");

            return kezappService.aggiorna(dto);
        }
    }
}