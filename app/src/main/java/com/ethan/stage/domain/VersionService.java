package com.ethan.stage.domain;

import com.ethan.stage.dal.VersionDO;
import com.ethan.stage.dal.VersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VersionService {

    @Autowired private VersionRepository versionRepository;

    public VersionDO getLastVersion() {
        // Pageable pageable = PageRequest.of(0, 1, Sort.by(Direction.DESC, "id"));
        return versionRepository.findAllByOrderByIdDesc().get(0);
    }
}
