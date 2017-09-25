package com.test.arithmetic.tree.traversal;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 先序遍历 postorder traversal  先输出根节点，然后输出子节点
 * Created by Ryan Miao on 9/24/17.
 */
public class PostorderTraversal {

    @Test
    public void testPostOrder(){
        String root = "/Users/ryan/workspace/learning/hexo-blog-src";
        int stop = 3;
        ArrayList<String> ignores = Lists.newArrayList(".git", ".deploy_git", "node_modules", ".DS_Store");

        printTree(root, stop, ignores);
    }

    private void printTree(String rootFile, int stop, List<String> ignores) {
        printTree(new File(rootFile), 0, stop, ignores, false);
    }
    private void printTree(File rootFile, int level, int stop, List<String> ignores, boolean last) {
        String name = rootFile.getName();
        if (level > stop || ignores.stream().anyMatch(name::contains)){
            return;
        }
        if (level == 0){
            System.out.println(".");
        }else {
            prettyPrint(level, rootFile, last);
        }

        if (rootFile.isDirectory()){
            File[] files = rootFile.listFiles();
            if (files != null ){
                int length = files.length;
                for (int i = 0; i < length ; i++) {
                    if (i == length - 1){
                        printTree(files[i], level+1, stop, ignores, true);
                    }else{
                        printTree(files[i], level+1, stop, ignores, false);
                    }
                }
            }
        }
    }

    private void prettyPrint(int level, File file,  boolean isLast) {
        StringBuilder sb = new StringBuilder();
        if (level != 1){
            sb.append("│");
        }

        for (int i = 0; i < level-2; i++) {
            sb.append("   |");
        }
        if (level != 1){
            sb.append("   ");
        }

        if (isLast){
            sb.append("└──");
        }else{
            sb.append("├──");
        }

        sb.append(file.getName());
        System.out.println(sb.toString());
    }
}
