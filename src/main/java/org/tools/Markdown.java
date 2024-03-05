package org.tools;

import com.vladsch.flexmark.html2md.converter.FlexmarkHtmlConverter;
import io.github.furstenheim.CopyDown;
import io.github.furstenheim.Options;
import io.github.furstenheim.OptionsBuilder;

/**
 *  <dependency>
 *             <groupId>com.vladsch.flexmark</groupId>
 *             <artifactId>flexmark-html2md-converter</artifactId>
 *             <version>0.64.0</version>
 *         </dependency>
 *         <dependency>
 *             <groupId>io.github.furstenheim</groupId>
 *             <artifactId>copy_down</artifactId>
 *             <version>1.0</version>
 *         </dependency>
 */

public class Markdown {
    public static String toMarkdown(String text) {
        OptionsBuilder optionsBuilder = OptionsBuilder.anOptions();
        Options options = optionsBuilder.withBr("-")
                // more options
                .build();
        CopyDown converter = new CopyDown(options);
        return converter.convert(text);
    }

    public static String flexToMarkdown(String html) {
        String md = FlexmarkHtmlConverter.builder().build().convert(html);
        return md;
    }
}
