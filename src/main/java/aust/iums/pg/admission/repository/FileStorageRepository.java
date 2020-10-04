package aust.iums.pg.admission.repository;

import aust.iums.pg.admission.model.FileStorage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Minhaz on 10/03/2020.
 */
public interface FileStorageRepository extends JpaRepository<FileStorage,Long> {
}
