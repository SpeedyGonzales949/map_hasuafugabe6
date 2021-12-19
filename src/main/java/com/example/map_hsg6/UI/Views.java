package com.example.map_hsg6.UI;

import com.example.map_hsg6.Controller.Controller;
import com.example.map_hsg6.Model.Course;
import com.example.map_hsg6.Model.Student;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * this class implements the view of the output data in the console
 */
public class Views {
    /**
     * thsi method implements the special View for {@link Controller#retrieveStudentsEnrolledForACourse(String)}
     * @param students Students enrolled for the course
     */
    public static @NotNull String retrieveStudentsEnrolledForACourseView(@NotNull List<Student> students){
        System.out.println(students);
        return students
                .stream()
                .map(student ->student.getFirstName()+" "+student.getLastName()+"\n")
                .reduce((student1,student2)->student1+student2)
                .get();
    }

    /**
     * this method implements the special View for {@link Controller#retrieveCoursesWithFreePlaces()}
     * @param courses Courses with free places
     */
    public static @NotNull String retrieveCoursesWithFreePlacesView(@NotNull List<Course>courses){
        return courses
                .stream()
                .map(course -> "Name:"+course.getName()+" Free Places:"+ (course.getMaxEnrollment() - course.getStudentsEnrolled().size()) +"/"+course.getMaxEnrollment()+"\n")
                .reduce((course1,course2)->course1+course2)
                .get();
    }

    /**
     * this method implements the special View for {@link Controller#register(Course, String)}
     * @param courses Courses for regisyering
     */
    public static @NotNull String  registerForCourseView(@NotNull List<Course>courses){
        AtomicReference<Integer> number= new AtomicReference<>(1);
        return courses
                .stream()
                .map(course -> (number.getAndSet(number.get() + 1))+"."+course.getName()+" Free Places:"+ (course.getMaxEnrollment() - course.getStudentsEnrolled().size())+"\n")
                .reduce((course1,course2)->course1+course2)
                .get();
    }
}
