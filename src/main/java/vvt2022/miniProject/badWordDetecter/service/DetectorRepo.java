package vvt2022.miniProject.badWordDetecter.service;

import java.util.List;

import org.springframework.ui.Model;

import vvt2022.miniProject.badWordDetecter.model.*;

public interface DetectorRepo {
    // create
    public void save(final Detector detector, final User user);

    // // read
    public void findByUser(Model model, final String username);

    // // update
    // public int update(final Detecter user);
}
