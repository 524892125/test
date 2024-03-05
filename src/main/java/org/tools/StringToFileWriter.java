package org.tools;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class StringToFileWriter {

    /**
     * 将字符串写入指定的文件，可以选择是否追加内容。
     * @param content 要写入文件的字符串内容
     * @param filePath 文件路径
     * @param append 是否追加到文件末尾（如果为false，则会覆盖原有文件内容）
     * @throws IOException 如果在写入过程中发生任何IO错误
     */
    public static void writeStringToFile(String content, String filePath, boolean append) throws IOException {
        // 创建FileWriter对象，并根据append参数决定是否追加模式
        FileWriter fileWriter = new FileWriter(filePath, append);

        // 使用BufferedWriter提高写入效率
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        // 写入字符串
        bufferedWriter.write(content);

        // 关闭流以确保数据被正确写入并释放系统资源
        bufferedWriter.close();
    }

    /**
     * 默认覆盖写入字符串到文件的方法
     * @param content 要写入的字符串内容
     * @param filePath 文件路径
     * @throws IOException 如果在写入过程中发生任何IO错误
     */
    public static void writeStringToFileOverwrite(String content, String filePath) throws IOException {
        writeStringToFile(content, filePath, false);
    }

    /**
     * 默认追加写入字符串到文件的方法
     * @param content 要追加的字符串内容
     * @param filePath 文件路径
     * @throws IOException 如果在写入过程中发生任何IO错误
     */
    public static void appendStringToFile(String content, String filePath) throws IOException {
        writeStringToFile(content, filePath, true);
    }
}

