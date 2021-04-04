package com.keepcoding.springboot.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserVersionController {

    @GetMapping("/v1/userVersioning")
    public UserVersionV1 getUserVersion1() {
        return new UserVersionV1("nombre");
    }

    @GetMapping("/v2/userVersioning")
    public UserVersion2 getUserVersion2() {
        return new UserVersion2(new NameStructure("nombre", "apellido"));
    }
}
