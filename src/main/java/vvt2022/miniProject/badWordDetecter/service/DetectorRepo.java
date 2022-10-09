package vvt2022.miniProject.badWordDetecter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.ui.Model;

import vvt2022.miniProject.badWordDetecter.model.Detector;
import vvt2022.miniProject.badWordDetecter.model.User;

public interface DetectorRepo {
    // create
    public void save(final Detector detector, final User user);

    // // read
    public Optional findByUser(Model model, final String username);
    public Optional<List<Detector>> get (final String username);
}
