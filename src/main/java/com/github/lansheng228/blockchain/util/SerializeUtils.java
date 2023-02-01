package com.github.lansheng228.blockchain.util;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

/**
 * 序列化工具类
 */
public class SerializeUtils {
    /**
     * 反序列化
     * bytes 对象对应的字节数组
     */
    public static Object deserialize(byte[] bytes) {
        Kryo kryo = new Kryo();
        kryo.setRegistrationRequired(false);

        Input input = new Input(bytes);
        Object obj = kryo.readClassAndObject(input);
        input.close();
        
        return obj;
    }

    /**
     * 序列化
     * object 需要序列化的对象
     */
    public static byte[] serialize(Object object) {
        Kryo kryo = new Kryo();
        kryo.setRegistrationRequired(false);

        Output output = new Output(4096, -1);
        kryo.writeClassAndObject(output, object);
        byte[] bytes = output.toBytes();
        output.close();

        return bytes;
    }
}

