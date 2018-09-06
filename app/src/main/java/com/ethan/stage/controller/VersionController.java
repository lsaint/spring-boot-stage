package com.ethan.stage.controller;

import com.ethan.stage.common.APIException;
import com.ethan.stage.service.VersionService;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {

    @Autowired VersionService versionService;
    @Autowired HttpServletRequest request;

    @RequestMapping(value = "/v1/version/last/", method = RequestMethod.GET)
    public String queryLastVersion() {
        if (true) {
            throw new APIException("test");
        }
        return versionService.getLastVersion().toString();
    }

    @RequestMapping(value = "/v1/version/", method = RequestMethod.POST)
    public String queryVersion(@Valid @RequestBody VersionRequestBody req) {
        return req.getClientType() + " " + req.getVersion();
    }
}

@Data
@NoArgsConstructor
class VersionRequestBody implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "请填写版本号")
    private String version;

    @JsonProperty("client_type")
    @Range(min = 1, max = 2, message = "客户端类型必须是1或2")
    private String clientType;
}
