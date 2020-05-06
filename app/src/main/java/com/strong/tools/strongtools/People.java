package com.strong.tools.strongtools;

import java.util.Objects;
import java.util.Random;

public class People {
    public int id;
    public String name;
    public String photo;

    public String[] photos = {"https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3984473917,238095211&fm=26&gp=0.jpg",
            "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2462146637,4274174245&fm=26&gp=0.jpg",
            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3880341262,3308316348&fm=26&gp=0.jpg",
            "https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=1141259048,554497535&fm=26&gp=0.jpg"};

    public People() {
    }

    public People(int id, String name, String photo) {
        this.id = id;
        this.name = name;
        photo = photos[Math.abs(new Random(System.currentTimeMillis()).nextInt() % 4)];
    }

    public void updatePeoplePhoto() {
        photo = photos[Math.abs(new Random(System.currentTimeMillis()).nextInt() % 4)];
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof People)) return false;
        People people = (People) o;
        return id == people.id &&
                Objects.equals(name, people.name) &&
                Objects.equals(photo, people.photo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, photo);
    }
}
