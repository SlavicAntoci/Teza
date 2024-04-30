package org.facultymanagementsystem.facultymanagementsystem.service;

import org.facultymanagementsystem.facultymanagementsystem.model.Curs;

import java.util.List;

public interface ICursService {
    List<Curs> getAllCurs();

    void addCurs(Curs curs);

    Boolean addUserToCurs(String courseCode, String userEmail);
    List<Curs> getCoursesForUser(String userEmail);
}
