package generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import common.CommonFunction;
import model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Generator {

    @Parameter(names={"--type", "-t"})
    String type;

    @Parameter(names={"--output", "-o"})
    String output;

    @Parameter(names={"--format", "-f"})
    String format;

    @Parameter(names={"--count", "-n"})
    int count;

    public static void main(String[] args) throws IOException {
        var generator = new Generator();
        JCommander.newBuilder()
                .addObject(generator)
                .build()
                .parse(args);
        generator.run();
    }

    private void run() throws IOException {
        var data = generate();
        save(data);
    }


    private Object generate() {
        if ("groups".equals(type)) {
            return generatorGroups();
        } else if ("contacts".equals(type)) {
            return generatorContact();
        } else {
            throw new IllegalArgumentException("Неизвестный тип данных" + type);
        }
    }

    private Object generatorGroups() {
        var result = new ArrayList<GroupData>();
        for (int i = 0; i < count; i++) {
            result.add(new GroupData()
                    .withName(CommonFunction.randomString(i * 10))
                    .withHeader(CommonFunction.randomString(i * 10))
                    .withFooter(CommonFunction.randomString(i * 10)));
        }
        return result;
    }

    private Object generatorContact() {
        return null;
    }

    private void save(Object data) throws IOException {
        if ("json".equals(format)) {
            ObjectMapper mapper =  new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            var json = mapper.writeValueAsString(data);

            try (var writer = new FileWriter(output)) {
                writer.write(json);
            }
        } else {
            throw new IllegalArgumentException("Неизвестный формат данных" + format);
        }
    }

//    private void save(Object data) throws IOException {
//        if ("json".equals(format)) {
//            ObjectMapper mapper =  new ObjectMapper();
//            mapper.enable(SerializationFeature.INDENT_OUTPUT);
//            mapper.writeValue(new File(output), data);
//        } else {
//            throw new IllegalArgumentException("Неизвестный формат данных" + format);
//        }
//    }
}
