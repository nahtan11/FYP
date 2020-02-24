/*
 * [The "BSD license"]
 * Copyright (c) 2011, abego Software GmbH, Germany (http://www.abego.org)
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 * 3. Neither the name of the abego Software GmbH nor the names of its
 *    contributors may be used to endorse or promote products derived from this
 *    software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package src.Results;

import org.abego.treelayout.TreeForTreeLayout;
import org.abego.treelayout.util.DefaultTreeForTreeLayout;

import javax.swing.*;

/**
 * Creates "Sample" trees, e.g. to be used in demonstrations.
 *
 * @author Udo Borkowski (ub@abego.org)
 */
public class SampleTreeFactory {

    /**
     * @return a "Sample" tree with {@link TextInBox} items as nodes.
     */
    public static TreeForTreeLayout<JTable> createSampleTree() {
        //TextInBox root = new TextInBox("1  2  3  4  5  \n6  7  8  9  10  \n11  12  13  14  15  \n16  17  18  19  20  \n21  22  23  24  X  ", 80, 80);
        Object[][] data = new Object[][] {
                {1, 2, 3, 4,5 },
                {6, 7, 8, 9,10 },
                {11, 12, 13, 14,15 },
                {16, 17, 18, 19,20 },
                {21, 22, 23, 24,"X" }
        };
        String[] columns = new String[] {
                "Id", "Name", "Hourly Rate", "Part Time","hello"
        };
        JTable root = new JTable(data,columns);
        JTable n1 = new JTable(data,columns);
        JTable n1_1 = new JTable(data,columns);
        JTable n1_2 = new JTable(data,columns);
        JTable n1_3 = new JTable(data,columns);
        JTable n2 = new JTable(data,columns);
        JTable n2_1 = new JTable(data,columns);
        //TextInBox n1 = new TextInBox("1  2  3  4  5  \n6  7  8  9  10  \n11  12  13  14  15  \n16  17  18  19  20  \n21  22  23  24  X  ", 80, 80);
        //TextInBox n1_1 = new TextInBox("1  2  3  4  5  \n6  7  8  9  10  \n11  12  13  14  15  \n16  17  18  19  20  \n21  22  23  24  X  ", 80, 80);
        //TextInBox n1_2 = new TextInBox("1  2  3  4  5  \n6  7  8  9  10  \n11  12  13  14  15  \n16  17  18  19  20  \n21  22  23  24  X  ", 80, 80);
        //TextInBox n1_3 = new TextInBox("1  2  3  4  5  \n6  7  8  9  10  \n11  12  13  14  15  \n16  17  18  19  20  \n21  22  23  24  X  ", 80, 80);
        //TextInBox n2 = new TextInBox("1  2  3  4  5  \n6  7  8  9  10  \n11  12  13  14  15  \n16  17  18  19  20  \n21  22  23  24  X  ", 80, 80);
        //TextInBox n2_1 = new TextInBox("1  2  3  4  5  \n6  7  8  9  10  \n11  12  13  14  15  \n16  17  18  19  20  \n21  22  23  24  X  ", 80, 80);

        DefaultTreeForTreeLayout<JTable> tree = new DefaultTreeForTreeLayout<JTable>(
                root);
        tree.addChild(root, n1);
        tree.addChild(n1, n1_1);
        tree.addChild(n1, n1_2);
        tree.addChild(n1, n1_3);
        tree.addChild(root, n2);
        tree.addChild(n2, n2_1);
        return tree;
    }

    /**
     * @return a "Sample" tree with {@link TextInBox} items as nodes.
     */
    public static TreeForTreeLayout<TextInBox> createSampleTree2() {
        TextInBox root = new TextInBox("prog", 40, 20);
        TextInBox n1 = new TextInBox("classDef", 65, 20);
        TextInBox n1_1 = new TextInBox("class", 50, 20);
        TextInBox n1_2 = new TextInBox("T", 20, 20);
        TextInBox n1_3 = new TextInBox("{", 20, 20);
        TextInBox n1_4 = new TextInBox("member", 60, 20);
        TextInBox n1_5 = new TextInBox("member", 60, 20);
        TextInBox n1_5_1 = new TextInBox("<ERROR:int>", 90, 20);
        TextInBox n1_6 = new TextInBox("member", 60, 20);
        TextInBox n1_6_1 = new TextInBox("int", 30, 20);
        TextInBox n1_6_2 = new TextInBox("i", 20, 20);
        TextInBox n1_6_3 = new TextInBox(";", 20, 20);
        TextInBox n1_7 = new TextInBox("}", 20, 20);


        DefaultTreeForTreeLayout<TextInBox> tree = new DefaultTreeForTreeLayout<TextInBox>(
                root);
        tree.addChild(root, n1);
        tree.addChild(n1, n1_1);
        tree.addChild(n1, n1_2);
        tree.addChild(n1, n1_3);
        tree.addChild(n1, n1_4);
        tree.addChild(n1, n1_5);
        tree.addChild(n1_5, n1_5_1);
        tree.addChild(n1, n1_6);
        tree.addChild(n1_6,n1_6_1);
        tree.addChild(n1_6,n1_6_2);
        tree.addChild(n1_6,n1_6_3);
        tree.addChild(n1, n1_7);
        return tree;
    }
}