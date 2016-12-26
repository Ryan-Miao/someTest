package com.test.security;

import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * https://www.owasp.org/index.php/Path_Manipulation
 *
 * Created by rmiao on 12/23/2016.
 */
public class FilePathTraversal {

    @Test
    public void avoidPathTraversal() throws URISyntaxException {
        String url = "/api/file?file=../../../etc/passwd";
        URI uri = new URI(url);
        URI normalize = uri.normalize();
        System.out.println(normalize);

        System.out.println(url.contains(".."));
    }
}
