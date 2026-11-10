package fr.epita.quiz.datamodel;

public class Question {
    private Long id;
    private String title;

    public Question() {}

    public Question(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Question(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}