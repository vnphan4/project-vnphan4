package com.projectvnphan4.projectvnphan4.Models;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CovidTrackRepo extends CrudRepository <CovidTrack, String> {

}