package com.tar.investnotes.utils;

//import com.fasterxml.jackson.core.JsonFactory;
//import com.fasterxml.jackson.core.JsonGenerator;
//import org.simpleflatmapper.csv.CsvParser;
//import org.simpleflatmapper.csv.CsvReader;
//import org.simpleflatmapper.csv.parser.CellConsumer;
//import org.simpleflatmapper.util.ErrorHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvToJsonNoString {

//    public static void main(String[] args) throws IOException {
//        CsvReader reader = CsvParser.reader("col1,col2\nval1,val2");
//
//        JsonFactory jsonFactory = new JsonFactory();
//
//
//        List<String> headers = new ArrayList<>();
//        if (reader.parseRow(CellConsumer.of(headers::add))) {
//
//            try (JsonGenerator jsonGenerator = jsonFactory.createGenerator(System.out)) {
//
//                jsonGenerator.writeStartArray();
//
//                reader.parseAll(new CellConsumer() {
//
//                    int index = 0;
//                    @Override
//                    public void newCell(char[] chars, int offset, int length) {
//                        try {
//                            if (index == 0) {
//                                jsonGenerator.writeStartObject();
//                            }
//
//                            if (index < headers.size()) {
//                                jsonGenerator.writeFieldName(headers.get(index));
//                                jsonGenerator.writeString(chars, offset, length);
//                            }
//
//                            index++;
//
//                        } catch (IOException e) {
//                            ErrorHelper.rethrow(e);
//                        }
//                    }
//
//                    @Override
//                    public boolean endOfRow() {
//                        try {
//                            if (index != 0) {
//                                jsonGenerator.writeEndObject();
//                            }
//                        } catch (IOException e) {
//                            ErrorHelper.rethrow(e);
//                        }
//                        index = 0;
//                        return true;
//                    }
//
//                    @Override
//                    public void end() {
//                        endOfRow();
//                    }
//                });
//
//                jsonGenerator.writeEndArray();
//            }
//        }
//    }
}