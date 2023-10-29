package generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import common.CommonFunction;
import model.ContactData;
import model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    private Object generateData(Supplier<Object> sataSupplier) {
        return Stream.generate(sataSupplier).limit(count).collect(Collectors.toList());
    }
//        var result = new ArrayList<Object>(); /* То же самое что выше в одну строку */
//        for (int i = 0; i < count; i++) {}
//        result.add(sataSupplier.get());
//        return result;
//    }

    private Object generatorGroups() {
        return generateData(() -> new GroupData()
                .withName(CommonFunction.randomString(10))
                .withHeader(CommonFunction.randomString(10))
                .withFooter(CommonFunction.randomString(10)));
    }

    private Object generatorContact() {
        return generateData(() -> new ContactData()
                .withFirstName(CommonFunction.randomString(10))
                .withLastName(CommonFunction.randomString(10))
                .withAddress(CommonFunction.randomString(10)));
    }

//    private Object generatorContact() {
//        var result = new ArrayList<ContactData>();
//        for (int i = 0; i < count; i++) {
//            result.add(new ContactData()
//                    .withFirstName(CommonFunction.randomString(i * 10))
//                    .withLastName(CommonFunction.randomString(i * 10))
//                    .withAddress(CommonFunction.randomString(i * 10)));
//        }
//        return result;
//    }

    private void save(Object data) throws IOException {
        if ("json".equals(format)) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            var json = mapper.writeValueAsString(data);

            try (var writer = new FileWriter(output)) {
                writer.write(json);
            }
        } if ("yaml".equals(format)) {
            var mapper = new YAMLMapper();
            mapper.writeValue(new File(output), data);
        } if ("xml".equals(format)) {
            var mapper = new XmlMapper();
            mapper.writeValue(new File(output), data);
        } else {
            throw new IllegalArgumentException("Неизвестный формат данных " + format);
        }
    }

//    private void save(Object data) throws IOException {
//        if ("json".equals(format)) {
//            ObjectMapper mapper =  new ObjectMapper();
//            mapper.enable(SerializationFeature.INDENT_OUTPUT);
//            mapper.writeValue(new File(output), data);
//        } else {
//            throw new IllegalArgumentException("Неизвестный формат данных " + format);
//        }
//    }
}
