package ru.job4j.question;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
       Map<Integer, String> pr = new HashMap<>();
        Iterator<User> prIterator = previous.iterator();
        while (prIterator.hasNext()) {
            User user = prIterator.next();
           pr.put(user.getId(), user.getName());
        }
       int added = 0;
       int changed = 0;

      Iterator<User> curIterator = current.iterator();
      while (curIterator.hasNext()) {
          User user = curIterator.next();
           String prName = pr.remove(user.getId());
           if (prName == null) {
               added++;
           } else if (!prName.equals(user.getName())) {
               changed++;
           }
       }
       int deleted = pr.size();
       return new Info(added, changed, deleted);
    }
}