import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDeque61BTest {

     /** In this test, we have three different assert statements that verify that addFirst works correctly. */
     @Test
     public void addFirstTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addFirst("back"); // after this call we expect: ["back"]
         assertThat(lld1.toList()).containsExactly("back").inOrder();

         lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

         lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
     }

     /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
      *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
     @Test
     public void addLastTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addLast("front"); // after this call we expect: ["front"]
         lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
         lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
     }

     /** This test performs interspersed addFirst and addLast calls. */
     @Test
     public void addFirstAndAddLastTest() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
         lld1.addLast(0);   // [0]
         lld1.addLast(1);   // [0, 1]
         lld1.addFirst(-1); // [-1, 0, 1]
         lld1.addLast(2);   // [-1, 0, 1, 2]
         lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

         assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
     }

    // Below, you'll write your own tests for LinkedListDeque61B.
    /** This test tests size in a normal case*/
    @Test
    public void sizeNormalTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

        lld1.addFirst(1); // [1]
        lld1.addFirst(2); // [2, 1]
        lld1.addLast(3);  // [2, 1, 3]
        lld1.addFirst(4); // [4, 2, 1, 3]
        lld1.addLast(5);  // [4, 2, 1, 3, 5]

        assertThat(lld1.size()).isEqualTo(5);
    }

    /** This test tests size in an empty case*/
    @Test
    public void sizeEmptyTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

        assertThat(lld1.size()).isEqualTo(0);
    }

    /** This test tests size while some items are negative*/
    @Test
    public void sizeNegativeTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

        lld1.addFirst(1); // [1]
        lld1.addFirst(-2); // [2, 1]
        lld1.addLast(3);  // [2, 1, 3]
        lld1.addFirst(-4); // [4, 2, 1, 3]
        lld1.addLast(5);  // [4, 2, 1, 3, 5]

        assertThat(lld1.size()).isEqualTo(5);
    }

    /** This test tests size while items are strings*/
    @Test
    public void sizeStringTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addFirst("abnormal"); // [1]
        lld1.addFirst("WTF"); // [2, 1]
        lld1.addLast("why");  // [2, 1, 3]
        lld1.addFirst("I'm"); // [4, 2, 1, 3]
        lld1.addLast("here");  // [4, 2, 1, 3, 5]

        assertThat(lld1.size()).isEqualTo(5);
    }

    /** This test tests size while some items are negative*/
    @Test
    public void sizeHugeTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

        for (int i = 0; i < 67656; i++) {
            lld1.addFirst(i);
            lld1.addLast(10086 - i);
        }

        assertThat(lld1.size()).isEqualTo(67656 * 2);
    }

    /** This test tests isEmpty in a normal case*/
    @Test
    public void isEmptyNormalTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

        assertThat(lld1.isEmpty()).isTrue();

        lld1.addFirst(1); // [1]
        lld1.addFirst(2); // [2, 1]
        lld1.addLast(3);  // [2, 1, 3]
        lld1.addFirst(4); // [4, 2, 1, 3]
        lld1.addLast(5);  // [4, 2, 1, 3, 5]

        assertThat(lld1.isEmpty()).isFalse();
    }

    /** This test tests isEmpty in a normal case*/
    @Test
    public void isEmptyStringTest() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        assertThat(lld1.isEmpty()).isTrue();

        lld1.addFirst("abnormal"); // [1]
        lld1.addFirst("WTF"); // [2, 1]
        lld1.addLast("why");  // [2, 1, 3]
        lld1.addFirst("I'm"); // [4, 2, 1, 3]
        lld1.addLast("here");  // [4, 2, 1, 3, 5]

        assertThat(lld1.isEmpty()).isFalse();
    }

    /** This test tests get in a normal case*/
    @Test
    public void getNormalTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

        assertThat(lld1.get(100)).isNull();

        lld1.addFirst(1); // [1]
        lld1.addFirst(2); // [2, 1]
        lld1.addLast(3);  // [2, 1, 3]
        lld1.addFirst(4); // [4, 2, 1, 3]
        lld1.addLast(5);  // [4, 2, 1, 3, 5]

        assertThat(lld1.get(4)).isEqualTo(5);
        assertThat(lld1.get(2)).isEqualTo(1);
    }

    /** This test tests get in a huge case*/
    @Test
    public void getHugeTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

        assertThat(lld1.get(100)).isNull();

        for (int i = 0; i < 67656; i++) {
            lld1.addFirst(i);
            lld1.addLast(10086 - i);
        }

        assertThat(lld1.get(45642)).isEqualTo(22013);
        assertThat(lld1.get(-2869)).isNull();
    }

    /** This test tests getRecursive in a normal case*/
    @Test
    public void getRecursiveNormalTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

        assertThat(lld1.getRecursive(100)).isNull();

        lld1.addFirst(1); // [1]
        lld1.addFirst(2); // [2, 1]
        lld1.addLast(3);  // [2, 1, 3]
        lld1.addFirst(4); // [4, 2, 1, 3]
        lld1.addLast(5);  // [4, 2, 1, 3, 5]

        assertThat(lld1.getRecursive(4)).isEqualTo(5);
        assertThat(lld1.getRecursive(2)).isEqualTo(1);
    }

    /** This test tests getRecursive in a huge case*/
    @Test
    public void getRecursiveHugeTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

        assertThat(lld1.getRecursive(100)).isNull();

        for (int i = 0; i < 6765; i++) {
            lld1.addFirst(i);
            lld1.addLast(10086 - i);
        }

        assertThat(lld1.getRecursive(4564)).isEqualTo(2200);
        assertThat(lld1.getRecursive(-2869)).isNull();
    }

    /** This tests removeFirst in a normal condition */
    @Test
    public void removeFirstTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.addLast(2);   // [-1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

        lld1.removeFirst();   // [-1, 0, 1, 2]

        assertThat(lld1.toList()).containsExactly(-1, 0, 1, 2).inOrder();
        assertThat(lld1.size()).isEqualTo(4);
    }

    /** This tests removeFirst in a normal condition */
    @Test
    public void removeFirstEmptyTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        lld1.addLast(0);   // [0]

        lld1.removeFirst();   // []

        assertThat(lld1.removeFirst()).isNull();
        assertThat(lld1.size()).isEqualTo(0);
    }

    /** This tests removeLast in a normal condition */
    @Test
    public void removeLastTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.addLast(2);   // [-1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

        lld1.removeLast();   // [-2, -1, 0, 1]

        assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1).inOrder();
        assertThat(lld1.size()).isEqualTo(4);
    }

    /** This tests removeLast in an empty condition */
    @Test
    public void removeLastEmptyTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        lld1.addLast(4);   // [0]
        lld1.removeLast();   // []

        assertThat(lld1.removeLast()).isNull();
        assertThat(lld1.size()).isEqualTo(0);
    }
}