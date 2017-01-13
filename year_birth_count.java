// 23 mins
public class year_birth_count {
  public static void main(String[] args) {

    Person[] people = new Person[6];
    people[0] = new Person(1900, 1982);
    people[1] = new Person(1901, 1904);
    people[2] = new Person(1902, 1904);
    people[3] = new Person(1903, 1904);
    people[4] = new Person(1904, 1982);
    people[5] = new Person(1905, 1982);

    System.out.println(yearWithMostPeople(people));
  }

  static int yearWithMostPeople(Person[] people) {
    int[] histogram = new int[100];

    for (Person p : people) {

      for (int i= p.birth - 1900; i< p.death - 1900; i++) {
        histogram[i]++;
      }
    }

    int max = 0;
    int max_year = 0;

    for (int i=0 ;i<histogram.length ; i++) {
      if (histogram[i] > max) {
        max = histogram[i];
        max_year = i + 1900;
      }
    }

    return max_year;
  }
}

class Person {
  public int birth;
  public int death;

  public Person(int birth, int death) {
    this.birth = birth;
    this.death = death;
  }
}
