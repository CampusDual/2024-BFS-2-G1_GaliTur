package com.campusdual.cd2024bfs2g1.ws.core.rest;

import com.campusdual.cd2024bfs2g1.api.core.service.IImageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ontimize.jee.server.rest.ORestController;
import com.sun.xml.bind.v2.schemagen.xmlschema.Any;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

import static org.bouncycastle.asn1.cms.CMSObjectIdentifiers.data;

@RestController
@RequestMapping("/images")
public class ImageRestController extends ORestController<IImageService> {

    @Autowired
    private IImageService imageService;

    @Override
    public IImageService getService() {
        return this.imageService;
    }

    //TODO necesario para cuando subamos más de una imagen
    @PostMapping(value = "/upload")
    public ResponseEntity upload(@RequestParam("name") String[] names, @RequestParam("file") MultipartFile[] files, @RequestParam("data") String data) throws JsonProcessingException {

            // Parameters received:
            // * names: array with the names of the uploaded files
            // * files: array with the uploaded files
            // * data: string with the data provided to the 'addiotional-data' attribute

            HashMap<String, Object> extraData = new HashMap<>();
            if (data != null) {
                extraData = new ObjectMapper().readValue(data, HashMap.class);
            }

            return new ResponseEntity(extraData, HttpStatus.OK);
    }
}
