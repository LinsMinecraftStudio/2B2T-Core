package org.lins.mmmjjkx.nex2b2tcore.utils;

import lombok.SneakyThrows;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.lins.mmmjjkx.nex2b2tcore.Nex2B2TCore;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class MessageHandler {
    private final Nex2B2TCore instance;
    private YamlConfiguration messages;

    public MessageHandler(Nex2B2TCore instance) {
        this.instance = instance;
        setup();
    }

    @SneakyThrows
    private void setup() {
        File file = new File(instance.getDataFolder(),"messages.yml");
        if (!file.exists()) {
            instance.saveResource("messages.yml",false);
        }

        File temp = File.createTempFile("messages","yml");
        InputStream stream = instance.getResource("messages.yml");
        Files.copy(stream, temp.toPath(), StandardCopyOption.REPLACE_EXISTING);

        //补全缺失消息
        YamlConfiguration original = YamlConfiguration.loadConfiguration(file);
        YamlConfiguration tempConfig = YamlConfiguration.loadConfiguration(temp);

        for (String key : tempConfig.getKeys(true)) {
            if (!original.contains(key)) {
                original.set(key, tempConfig.get(key));
            }
        }

        messages = original;
    }

    public void reload() {
        setup();
    }

    public String get(String key) {
        return messages.getString(key, "找不到消息: "+ key);
    }

    public String colorize(String context) {
        return ChatColor.translateAlternateColorCodes('&', context);
    }

    public String getColored(String key) {
        return colorize(get(key));
    }

    public void send(CommandSender sender, String key) {
        sender.sendMessage(getColored(key));
    }
}
