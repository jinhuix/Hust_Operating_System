package hust.cs.javacourse.search.index.impl;

import hust.cs.javacourse.search.index.AbstractDocument;
import hust.cs.javacourse.search.index.AbstractDocumentBuilder;
import hust.cs.javacourse.search.index.AbstractIndex;
import hust.cs.javacourse.search.index.AbstractIndexBuilder;

import java.io.File;

/**
 * AbstractIndexBuilder的具体实现类.
 */
public class IndexBuilder extends AbstractIndexBuilder {
    public IndexBuilder(AbstractDocumentBuilder docBuilder) {
        super(docBuilder);
    }

    /**
     * <pre>
     * 构建指定目录下的所有文本文件的倒排索引.
     *      需要遍历和解析目录下的每个文本文件, 得到对应的Document对象，再依次加入到索引，并将索引保存到文件.
     * @param rootDirectory ：指定目录
     * @return ：构建好的索引
     * </pre>
     */
    @Override
    public AbstractIndex buildIndex(String rootDirectory) {
        File path = new File(rootDirectory);
        Index index = new Index();
        if (path.isDirectory()){//是否为文件夹
            File[] files = path.listFiles();
            if (files != null){//目录下有文件
                for (File file : files){
                    if (file.isFile()){//若有子目录跳过子目录
                        AbstractDocument document = this.docBuilder.build(this.docId,file.getPath(),file);
                        index.addDocument(document);
                        this.docId++;
                    }
                }
                return index;
            }
        }
        return null;
    }
}
