package ru.job4j.generics;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RoleStoreTest {

    @Test
    void whenAddAndFindThenUsernameIsPetr() {
        UserStore store = new UserStore();
        store.add(new User("3", "Vova"));
        User result = store.findById("3");
        assertThat(result.getUsername()).isEqualTo("Vova");
    }

    @Test
    void whenAddAndFindThenUserIsNull() {
        UserStore store = new UserStore();
        store.add(new User("2", "Anna"));
        User result = store.findById("5");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindUsernameIsPetr() {
        UserStore store = new UserStore();
        store.add(new User("3", "Kolya"));
        store.add(new User("3", "Sergey"));
        User result = store.findById("3");
        assertThat(result.getUsername()).isEqualTo("Kolya");
    }

    @Test
    void whenReplaceThenUsernameIsMaxim() {
        UserStore store = new UserStore();
        store.add(new User("5", "Vova"));
        store.replace("5", new User("5", "Masha"));
        User result = store.findById("5");
        assertThat(result.getUsername()).isEqualTo("Masha");
    }

    @Test
    void whenNoReplaceUserThenNoChangeUsername() {
        UserStore store = new UserStore();
        store.add(new User("1", "Vanya"));
        store.replace("10", new User("10", "Stas"));
        User result = store.findById("1");
        assertThat(result.getUsername()).isEqualTo("Vanya");
    }

    @Test
    void whenDeleteUserThenUserIsNull() {
        UserStore store = new UserStore();
        store.add(new User("7", "Larisa"));
        store.delete("7");
        User result = store.findById("7");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteUserThenUsernameIsPetr() {
        UserStore store = new UserStore();
        store.add(new User("7", "Larisa"));
        store.delete("3");
        User result = store.findById("7");
        assertThat(result.getUsername()).isEqualTo("Larisa");
    }

    @Test
    void whenReplaceOkThenTrue() {
        UserStore store = new UserStore();
        store.add(new User("4", "Kolya"));
        boolean result = store.replace("4", new User("4", "Vanya"));
        assertThat(result).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        UserStore store = new UserStore();
        store.add(new User("2", "Petr"));
        boolean result = store.replace("5", new User("5", "Anna"));
        assertThat(result).isFalse();
    }
}
