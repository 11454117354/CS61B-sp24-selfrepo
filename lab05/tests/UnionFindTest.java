import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
import static org.junit.Assert.fail;

public class UnionFindTest {

    /**
     * Checks that the initial state of the disjoint sets are correct (this will pass with the skeleton
     * code, but ensure it still passes after all parts are implemented).
     */
    @Test
    public void initialStateTest() {
        UnionFind uf = new UnionFind(4);
        assertThat(uf.connected(0, 1)).isFalse();
        assertThat(uf.connected(0, 2)).isFalse();
        assertThat(uf.connected(0, 3)).isFalse();
        assertThat(uf.connected(1, 2)).isFalse();
        assertThat(uf.connected(1, 3)).isFalse();
        assertThat(uf.connected(2, 3)).isFalse();
    }

    /**
     * Checks that invalid inputs are handled correctly.
     */
    @Test
    public void illegalFindTest() {
        UnionFind uf = new UnionFind(4);
        try {
            uf.find(10);
            fail("Cannot find an out of range vertex!");
        } catch (IllegalArgumentException e) {
            return;
        }
        try {
            uf.union(1, 10);
            fail("Cannot union with an out of range vertex!");
        } catch (IllegalArgumentException e) {
            return;
        }
    }

    /**
     * Checks that union is done correctly (including the tie-breaking scheme).
     */
    @Test
    public void basicUnionTest() {
        UnionFind uf = new UnionFind(10);
        uf.union(0, 1);
        assertThat(uf.find(0)).isEqualTo(1);
        uf.union(2, 3);
        assertThat(uf.find(2)).isEqualTo(3);
        uf.union(0, 2);
        assertThat(uf.find(1)).isEqualTo(3);

        uf.union(4, 5);
        uf.union(6, 7);
        uf.union(8, 9);
        uf.union(4, 8);
        uf.union(4, 6);

        assertThat(uf.find(5)).isEqualTo(9);
        assertThat(uf.find(7)).isEqualTo(9);
        assertThat(uf.find(8)).isEqualTo(9);

        uf.union(9, 2);
        assertThat(uf.find(3)).isEqualTo(9);
    }

    /**
     * Unions the same item with itself. Calls on find and checks that the outputs are correct.
     */
    @Test
    public void sameUnionTest() {
        UnionFind uf = new UnionFind(4);
        uf.union(1, 1);
        for (int i = 0; i < 4; i += 1) {
            assertThat(uf.find(i)).isEqualTo(i);
        }
    }

    /**
     * Write your own tests below here to verify for correctness. The given tests are not comprehensive.
     * Specifically, you may want to write a test for path compression and to check for the correctness
     * of all methods in your implementation.
     */

    @Test
    public void sizeOfAndParentTest() {
        UnionFind uf = new UnionFind(6);
        // 初始 size 全为 1
        for (int i = 0; i < 6; i++) {
            assertThat(uf.sizeOf(i)).isEqualTo(1);
            assertThat(uf.parent(i)).isEqualTo(-1); // 负号代表只有自己
        }

        uf.union(0, 1);
        uf.union(2, 3);
        uf.union(0, 2); // 合并 {0,1} 和 {2,3}
        // 现在这三个集合大小应为 4
        assertThat(uf.sizeOf(0)).isEqualTo(4);
        assertThat(uf.sizeOf(1)).isEqualTo(4);
        assertThat(uf.sizeOf(2)).isEqualTo(4);
        assertThat(uf.sizeOf(3)).isEqualTo(4);

        // 对未合并的元素 4 和 5
        assertThat(uf.sizeOf(4)).isEqualTo(1);
        assertThat(uf.sizeOf(5)).isEqualTo(1);

        // parent of root must be negative (size)
        assertThat(uf.parent(uf.find(0))).isLessThan(0);
    }

    @Test
    public void chainUnionAndConnectedTest() {
        UnionFind uf = new UnionFind(5);
        // 逐步链式合并
        uf.union(0, 1);
        uf.union(1, 2);
        uf.union(2, 3);

        // 以前不连的变连了
        assertThat(uf.connected(0, 3)).isTrue();
        assertThat(uf.connected(0, 4)).isFalse();

        // 加上最后一个
        uf.union(3, 4);
        assertThat(uf.connected(0, 4)).isTrue();
    }

    @Test
    public void pathCompressionEffectTest() {
        UnionFind uf = new UnionFind(7);

        uf.union(0, 1);
        uf.union(1, 2);
        uf.union(2, 3);
        uf.union(3, 4);

        int before = uf.find(0); // 找到最终 root
        // 强制触发 path compression
        uf.find(4);
        // 再次 find 应该直接到 root（中间节点 parents 变）
        assertThat(uf.parent(0)).isEqualTo(uf.find(0));
        assertThat(uf.parent(1)).isEqualTo(-5);
    }

    @Test
    public void repeatedUnionDoesNotChangeSize() {
        UnionFind uf = new UnionFind(4);
        uf.union(0, 1);
        int sizeBefore = uf.sizeOf(0);

        // 再次 union 同一集合不会改变 size
        uf.union(0, 1);
        assertThat(uf.sizeOf(0)).isEqualTo(sizeBefore);
        assertThat(uf.sizeOf(1)).isEqualTo(sizeBefore);
    }

    // 额外 edge case：各种非法输入
    @Test
    public void illegalInputsTest() {
        UnionFind uf = new UnionFind(3);

        // find out-of-bounds
        try {
            uf.find(-1);
            fail("find 不应该支持负数下标");
        } catch (IllegalArgumentException ignored) {}

        try {
            uf.find(100);
            fail("find 不应该支持越界下标");
        } catch (IllegalArgumentException ignored) {}

        // union out-of-bounds
        try {
            uf.union(-1, 0);
            fail("union 不应该支持负数下标");
        } catch (IllegalArgumentException ignored) {}

        try {
            uf.union(1, 10);
            fail("union 不应该支持越界下标");
        } catch (IllegalArgumentException ignored) {}
    }

}


