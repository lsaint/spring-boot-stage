package com.ethan.stage.service;

import com.ethan.stage.dal.Version;
import com.ethan.stage.dal.VersionRepository;
import com.ethan.stage.service.BO.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EthanServiceImpl implements EthanService {

    @Autowired private VersionRepository versionRepository;

    public Page<Version> getPageQueryVersion() {
        // PageRequest.of(int page, int size, Sort.Direction direction, String... properties)
        Pageable pageable = PageRequest.of(0, 1, Sort.by(Direction.DESC, "id"));
        return versionRepository.findAll(pageable);
    }

    public String getUrl() {
        RestTemplate restTemplate = new RestTemplate();
        Quote quote =
                restTemplate.getForObject(
                        "http://gturnquist-quoters.cfapps.io/api/random", Quote.class);
        return quote.toString();
    }
}
