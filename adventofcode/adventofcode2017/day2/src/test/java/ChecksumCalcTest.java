import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChecksumCalcTest {

    @Test
    public void TestSum() {
        ChecksumCalc c = new ChecksumCalc();

        assertEquals(8, c.sumCheck("5 1 9 5"));
        String s = "5 1 9 5\n" +
                "7 5 3\n" +
                "2 4 6 8";
        assertEquals(18, c.sumCheck(s));

        // Day 1
        // Parse a list of integers, tab and newline separated
        // For each line, find min/max and calc diff
        // Sum all the diffs
        // This is my checksum!
        assertEquals(41919, c.sumCheck(puzzleInput));
    }

    @Test
    public void TestDivides() {
        ChecksumCalc c = new ChecksumCalc();
        assertEquals(4, c.dividesCheck("5 9 2 8"));
        assertEquals(3, c.dividesCheck("9 4 7 3"));
        assertEquals(303, c.dividesCheck(puzzleInput));
    }

    String puzzleInput = "798\t1976\t1866\t1862\t559\t1797\t1129\t747\t85\t1108\t104\t2000\t248\t131\t87\t95\n" +
            "201\t419\t336\t65\t208\t57\t74\t433\t68\t360\t390\t412\t355\t209\t330\t135\n" +
            "967\t84\t492\t1425\t1502\t1324\t1268\t1113\t1259\t81\t310\t1360\t773\t69\t68\t290\n" +
            "169\t264\t107\t298\t38\t149\t56\t126\t276\t45\t305\t403\t89\t179\t394\t172\n" +
            "3069\t387\t2914\t2748\t1294\t1143\t3099\t152\t2867\t3082\t113\t145\t2827\t2545\t134\t469\n" +
            "3885\t1098\t2638\t5806\t4655\t4787\t186\t4024\t2286\t5585\t5590\t215\t5336\t2738\t218\t266\n" +
            "661\t789\t393\t159\t172\t355\t820\t891\t196\t831\t345\t784\t65\t971\t396\t234\n" +
            "4095\t191\t4333\t161\t3184\t193\t4830\t4153\t2070\t3759\t1207\t3222\t185\t176\t2914\t4152\n" +
            "131\t298\t279\t304\t118\t135\t300\t74\t269\t96\t366\t341\t139\t159\t17\t149\n" +
            "1155\t5131\t373\t136\t103\t5168\t3424\t5126\t122\t5046\t4315\t126\t236\t4668\t4595\t4959\n" +
            "664\t635\t588\t673\t354\t656\t70\t86\t211\t139\t95\t40\t84\t413\t618\t31\n" +
            "2163\t127\t957\t2500\t2370\t2344\t2224\t1432\t125\t1984\t2392\t379\t2292\t98\t456\t154\n" +
            "271\t4026\t2960\t6444\t2896\t228\t819\t676\t6612\t6987\t265\t2231\t2565\t6603\t207\t6236\n" +
            "91\t683\t1736\t1998\t1960\t1727\t84\t1992\t1072\t1588\t1768\t74\t58\t1956\t1627\t893\n" +
            "3591\t1843\t3448\t1775\t3564\t2632\t1002\t3065\t77\t3579\t78\t99\t1668\t98\t2963\t3553\n" +
            "2155\t225\t2856\t3061\t105\t204\t1269\t171\t2505\t2852\t977\t1377\t181\t1856\t2952\t2262";
}