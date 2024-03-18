package com.malykhnik.bulletinboard.initialization;

import com.malykhnik.bulletinboard.entity.Message;
import com.malykhnik.bulletinboard.entity.Topic;
import com.malykhnik.bulletinboard.repository.InMemoryTopicDB;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Component
@AllArgsConstructor
public class InMemoryDBInitializer implements ApplicationRunner {
    private final InMemoryTopicDB repo;
    @Override
    public void run(ApplicationArguments args) {
        List<Topic> init = new ArrayList<>();
        if (!repo.getTopics().isEmpty()) return;
        // Topic 1
        List<Message> topic1Messages = new ArrayList<>();
        topic1Messages.add(new Message(1, "John", "Hello, everyone!", Calendar.getInstance().getTime()));
        topic1Messages.add(new Message(2, "Alice", "Welcome to our community!", Calendar.getInstance().getTime()));
        Topic topic1 = new Topic(1, "Introduction", topic1Messages);
        init.add(topic1);

        // Topic 2
        List<Message> topic2Messages = new ArrayList<>();
        topic2Messages.add(new Message(1, "Bob", "New feature announcement!", Calendar.getInstance().getTime()));
        topic2Messages.add(new Message(2, "Sarah", "Looking for feedback", Calendar.getInstance().getTime()));
        Topic topic2 = new Topic(2, "Product Updates", topic2Messages);
        init.add(topic2);

        // Topic 3
        List<Message> topic3Messages = new ArrayList<>();
        topic3Messages.add(new Message(1, "Mark", "Important notice regarding security", Calendar.getInstance().getTime()));
        Topic topic3 = new Topic(3, "Security Alert", topic3Messages);
        init.add(topic3);

        // Topic 4
        List<Message> topic4Messages = new ArrayList<>();
        topic4Messages.add(new Message(1, "Emily", "Event announcement: Save the date!", Calendar.getInstance().getTime()));
        topic4Messages.add(new Message(2, "Jake", "Event details and RSVP", Calendar.getInstance().getTime()));
        topic4Messages.add(new Message(3, "Olivia", "Agenda for the event", Calendar.getInstance().getTime()));
        Topic topic4 = new Topic(4, "Upcoming Event", topic4Messages);
        init.add(topic4);

        // Topic 5
        List<Message> topic5Messages = new ArrayList<>();
        topic5Messages.add(new Message(1, "Tom", "Project update: Milestone achieved", Calendar.getInstance().getTime()));
        topic5Messages.add(new Message(2, "Linda", "Next steps and action items", Calendar.getInstance().getTime()));
        Topic topic5 = new Topic(5, "Project Progress", topic5Messages);
        init.add(topic5);

        // Topic 6
        List<Message> topic6Messages = new ArrayList<>();
        topic6Messages.add(new Message(1, "Chris", "Feedback requested on new feature", Calendar.getInstance().getTime()));
        topic6Messages.add(new Message(2, "Sophia", "Bug report: Issue found in module", Calendar.getInstance().getTime()));
        topic6Messages.add(new Message(3, "Aiden", "Feature enhancement suggestions", Calendar.getInstance().getTime()));
        topic6Messages.add(new Message(4, "Emma", "Question about functionality", Calendar.getInstance().getTime()));
        Topic topic6 = new Topic(6, "Feature Feedback", topic6Messages);
        init.add(topic6);

        repo.setTopics(init);
    }
}
