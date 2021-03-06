package com.kodcu.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.util.Arrays;
import java.util.List;

/**
 * Created by usta on 07.09.2014.
 */
@Component
public class PathResolverService {

    private static final Logger logger = LoggerFactory.getLogger(PathResolverService.class);

    final PathMatcher pdfMatcher = FileSystems.getDefault().getPathMatcher("glob:**.pdf");
    final PathMatcher markdownMatcher = FileSystems.getDefault().getPathMatcher("glob:{**.md,**.markdown}");
    final PathMatcher htmlMatcher = FileSystems.getDefault().getPathMatcher("glob:**.{htm,html}");
    final PathMatcher docBookMatcher = FileSystems.getDefault().getPathMatcher("glob:**.xml");
    final PathMatcher ascMatcher = FileSystems.getDefault().getPathMatcher("glob:**.{asc,asciidoc,ad,adoc,txt}");
    final PathMatcher imageMatcher = FileSystems.getDefault().getPathMatcher("glob:**.{png,svg,jpg,jpeg,bmp,gif}");
    final PathMatcher pptMatcher = FileSystems.getDefault().getPathMatcher("glob:**.{ppt,pptx}");
    final PathMatcher docxMatcher = FileSystems.getDefault().getPathMatcher("glob:**.{doc,docx}");
    final PathMatcher excelMatcher = FileSystems.getDefault().getPathMatcher("glob:**.{xls,xlsx}");
    final PathMatcher archieveMatcher = FileSystems.getDefault().getPathMatcher("glob:**.{zip,jar,tar,rar,tar.gz}");
    final PathMatcher videoMatcher = FileSystems.getDefault().getPathMatcher("glob:**.{cda,avi,flv,mkv,mov,mp4,mpeg,mpg,ogv,webm,divx,wmv}");
    final PathMatcher cssMatcher = FileSystems.getDefault().getPathMatcher("glob:**.{css,css3,scss,less}");
    final PathMatcher terminalMatcher = FileSystems.getDefault().getPathMatcher("glob:**.{bat,sh,cmd}");
    final PathMatcher codeMatcher = FileSystems.getDefault().getPathMatcher("glob:**.{asp,aspx,c,cpp,java,js,aj,php,rb,yml,py}");
    final PathMatcher epubMatcher = FileSystems.getDefault().getPathMatcher("glob:**.{epub,epub3}");
    final PathMatcher mobiMatcher = FileSystems.getDefault().getPathMatcher("glob:**.{mobi,azw,azw3}");
    final PathMatcher anyMatcher = FileSystems.getDefault().getPathMatcher("glob:**.{*}");
    final PathMatcher uniqueMatcher = FileSystems.getDefault().getPathMatcher("glob:{license,readme,gradlew}");
    final PathMatcher bookMatcher = FileSystems.getDefault().getPathMatcher("glob:{**book.asc,**book.txt,**book.asciidoc,**book.adoc,**book.ad}");

    public boolean isPDF(Path path) {
        return pdfMatcher.matches(path);
    }

    public boolean isImage(Path path) {
        return imageMatcher.matches(path);
    }

    public boolean isHidden(Path path) {
        try {
            return path.getFileName().toString().startsWith(".") || Files.isHidden(path);
        } catch (IOException e) {
            logger.info(e.getMessage(), e);
        }
        return false;
    }

    public boolean isMarkdown(Path path) {
        return markdownMatcher.matches(path);
    }

    public boolean isXML(Path path) {
        return docBookMatcher.matches(path);
    }

    public boolean isHTML(Path path) {
        return htmlMatcher.matches(path);
    }

    public boolean isAsciidoc(Path path) {
        return ascMatcher.matches(path);
    }

    public boolean isViewable(Path path) {
        return Files.isDirectory(path)
                || isAsciidoc(path)
                || isImage(path)
                || isPDF(path)
                || isEpub(path)
                || isMobi(path)
                || isHTML(path)
                || isXML(path)
                || isMarkdown(path);
    }


    public boolean isBook(Path path) {
        return bookMatcher.matches(path);
    }

    public boolean isMobi(Path path) {
        return mobiMatcher.matches(path);
    }

    public boolean isPPT(Path path) {
        return pptMatcher.matches(path);
    }

    public boolean isDocx(Path path) {
        return docxMatcher.matches(path);
    }

    public boolean isExcel(Path path) {
        return excelMatcher.matches(path);
    }

    public boolean isArchive(Path path) {
        return archieveMatcher.matches(path);
    }

    public boolean isVideo(Path path) {
        return videoMatcher.matches(path);
    }

    public boolean isCSS(Path path) {
        return cssMatcher.matches(path);
    }

    public boolean isBash(Path path) {
        return terminalMatcher.matches(path);
    }

    public boolean isCode(Path path) {
        return codeMatcher.matches(path);
    }

    public boolean isAny(Path path) {
        return anyMatcher.matches(path) || uniqueMatcher.matches(path);
    }

    public boolean isEpub(Path path) {
        return epubMatcher.matches(path);
    }
}
