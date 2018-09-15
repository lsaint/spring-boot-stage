package com.ethan.stage.dal;

import java.util.List;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface VersionRepository extends PagingAndSortingRepository<VersionDO, Long> {
    public List<VersionDO> findAllByOrderByIdDesc();
}
