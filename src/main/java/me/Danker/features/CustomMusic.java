package me.Danker.features;

import me.Danker.DankersSkyblockMod;
import me.Danker.config.ModConfig;
import me.Danker.events.PostConfigInitEvent;
import me.Danker.handlers.ScoreboardHandler;
import me.Danker.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StringUtils;
import net.minecraft.world.World;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.sound.PlaySoundEvent;
import net.minecraftforge.event.entity.player.PlayerUseItemEvent.Stop;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CustomMusic {

    static boolean cancelNotes;

    public static Song dungeonboss;
    public static Song bloodroom;
    public static Song dungeon;
    public static Song floorsix;
    public static Song phase1;
    public static Song phase2;
    public static Song phase3;
    public static Song phase4;
    public static Song phase5;
    public static Song hub;
    public static Song island;
    public static Song dungeonHub;
    public static Song farmingIslands;
    public static Song garden;
    public static Song goldMine;
    public static Song deepCaverns;
    public static Song dwarvenMines;
    public static Song crystalHollows;
    public static Song spidersDen;
    public static Song crimsonIsle;
    public static Song kuudra;
    public static Song end;
    public static Song park;
    public static Song jerryw;
    public static Song jerryfight;
    public static Song jerryprefight;
    public static Song bloodroomend;

    public static Song jingle_jerry_win;
    public static Song jingle_jerry_lose;
    public static Song jingle_f6_win;
    public static Song jingle_f6_lose;
    public static Song jingle_f7_win;
    public static Song jingle_f7_lose;

    public static Song dojo;
    public static Song dojostart;

    static int curPhase = 0;
    public static int jingle7 = 0;
    public static int jinglej = 0;
    public static boolean stopMusic = true;

    public static boolean isInJerrysWorkshop = false;
    public static boolean isFightingCubes = false;
    public static boolean currentlyFightingCubes = false; // variable above me stupid >:(
    public static boolean canPlayJerryMusic = true; //please work

    public static boolean hasPlayedIntro = false;
    public static boolean loopable = false;
    public static boolean f6dungeonbossmusicstopperhehelol = false; //reminder to delete this when support for all dungeon floors is done

    public static int ticksRemaining = 0;
    public static boolean canCountTicks = false;

    @SubscribeEvent
    public void onWorldChange(WorldEvent.Load event) {
        reset();
    }

    @SubscribeEvent
    public void onWorldUnload(WorldEvent.Unload event) {
        reset();
    }

    @SubscribeEvent
    public void onDisconnect(FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
        reset();
    }   

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onTick(TickEvent.ClientTickEvent event) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        if (event.phase != TickEvent.Phase.START) return;

        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayerSP player = mc.thePlayer;
        World world = mc.theWorld;
        if (DankersSkyblockMod.tickAmount % 20 == 0) {
            if (world != null && player != null) {
                if (ModConfig.dungeonBossMusic) {
                    if (!stopMusic) {
                        switch (curPhase) {
                           case -1:
                               break;
                           case 2:
                               phase2.start();
                               break;
                          case 3:
                              phase3.start();
                              break;
                          case 4:
                              phase4.start();
                              break;
                         case 5:
                             phase5.start();
                             break;
                         case 6:
                             phase1.start();
                             break;
                         default:
                            if (!f6dungeonbossmusicstopperhehelol) dungeonboss.start();
                        }
                    }
                }
                if (ModConfig.jinglef7Music){
                    switch (jingle7) {
                       case 1:
                            jingle_f7_lose.start();
                            jingle7 = 0;
                            break;
                        case 2:
                            jingle_f7_win.start();
                            jingle7 = 0;
                            break;
                    }
                }
                if (Utils.isInDungeons()) {
                    List<String> scoreboard = ScoreboardHandler.getSidebarLines();
                    if (scoreboard.size() > 2) {
                        String firstLine = ScoreboardHandler.cleanSB(scoreboard.get(scoreboard.size() - 1));
                        String secondLine = ScoreboardHandler.cleanSB(scoreboard.get(scoreboard.size() - 2));
                        if (firstLine.contains("30,30") || // F1
                            firstLine.contains("30,125") || // F2
                            firstLine.contains("30,225") || // F3
                            secondLine.contains("- Healthy") || // F3
                            firstLine.contains("30,344") || // F4
                            firstLine.contains("livid") || // F5
                            firstLine.contains("sadan") || // F6
                            firstLine.contains("maxor") || // F7
                            firstLine.contains("f7")) {
                        }
                    }
                } else {
                    switch (Utils.currentLocation) {
                        case HUB:
                            if (ModConfig.hubMusic) hub.start();
                            break;
                        case PRIVATE_ISLAND:
                            if (ModConfig.islandMusic) island.start();
                            break;
                        case DUNGEON_HUB:
                            if (ModConfig.dungeonHubMusic) dungeonHub.start();
                            break;
                        case FARMING_ISLANDS:
                            if (ModConfig.farmingIslandsMusic) farmingIslands.start();
                            break;
                        case GARDEN:
                            if (ModConfig.gardenMusic) garden.start();
                            break;
                        case GOLD_MINE:
                            if (ModConfig.goldMineMusic) goldMine.start();
                            break;
                        case DEEP_CAVERNS:
                            if (ModConfig.deepCavernsMusic) deepCaverns.start();
                            break;
                        case DWARVEN_MINES:
                            if (ModConfig.dwarvenMinesMusic) dwarvenMines.start();
                            break;
                        case CRYSTAL_HOLLOWS:
                            if (ModConfig.crystalHollowsMusic) crystalHollows.start();
                            break;
                        case SPIDERS_DEN:
                            if (ModConfig.spidersDenMusic) spidersDen.start();
                            break;
                        case CRIMSON_ISLE:
                            if (ModConfig.crimsonIsleMusic) {
                                if (Utils.sublocationUtil() == "Dojo") {
                                    if (dojo.hasSongs()) {
                                        if (dojostart.hasSongs()) {
                                            if (!hasPlayedIntro) {
                                                if (!loopable) dojostart.start();
                                                crimsonIsle.stop();
                                                hasPlayedIntro = true;
                                            } else if(hasPlayedIntro) {
                                                crimsonIsle.stop();
                                                if (!dojostart.music.isRunning()) {
                                                    dojo.start();
                                                    loopable = true;
                                                } 
                                            }
                                        } else {
                                            dojo.start();
                                        }
                                    }
                                } else if (Utils.sublocationUtil() == "Normal") {
                                    dojo.stop();
                                    dojostart.stop();
                                    hasPlayedIntro = false;
                                    loopable = false;
                                    if (crimsonIsle.hasSongs()) {
                                        crimsonIsle.start();
                                    }
                                }
                            }
                            break;
                        case KUUDRA:
                            if (ModConfig.kuudraMusic) kuudra.start();
                            break;
                        case END:
                            if (ModConfig.endMusic) end.start();
                            break;
                        case PARK:
                            if (ModConfig.parkMusic) park.start();
                            break;
                        case JERRY_WORKSHOP:
                            isInJerrysWorkshop = true;
                            if (ModConfig.jerryMusic) {

                            if (canPlayJerryMusic && !canCountTicks && !currentlyFightingCubes) jerryw.start();
                    
                            if (canCountTicks) {
                                if (ticksRemaining > 0) { //if there are ticks remaining and you can count ticks
                                    ticksRemaining--; //take out one tick remaining 
                                } else if (ticksRemaining == 0) { //if there are no ticks remaining and you can count ticks
                                    canCountTicks = false; //no longer can count ticks
                                    canPlayJerryMusic = true;
                                    jinglej = 0;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @SubscribeEvent(receiveCanceled = true)
    public void onChat(ClientChatReceivedEvent event) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        String message = StringUtils.stripControlCodes(event.message.getUnformattedText());

        if (ModConfig.dungeonMusic && Utils.isInDungeons()) {
            if (message.contains("[NPC] Mort: Here, I found this map when I first entered the dungeon.")) {
                dungeon.start();
            }
        }
        //}
        //if (Utils.isInDungeons()) {
        if (ModConfig.dungeonBossMusic) {
        
            // F7 music handler (very complicated for some reason probably bc it has a lot of phases LOL)
            if (phase2.hasSongs() && message.startsWith("[BOSS] Maxor: I'M TOO YOUNG TO DIE AGAIN!")) {
                phase2.start();
                curPhase = 2;
            } else if (phase3.hasSongs() && message.startsWith("[BOSS] Goldor: Who dares trespass into my domain")) {
                phase3.start();
                curPhase = 3;
            } else if (phase4.hasSongs() && message.startsWith("[BOSS] Necron: You went further than any human before")) {
                phase4.start();
                curPhase = 4;
            } else if (phase5.hasSongs() && message.startsWith("[BOSS] ") && message.endsWith("You.. again?")) {
                phase5.start();
                curPhase = 5;
            } else if (phase1.hasSongs() && message.startsWith("[BOSS] Maxor: WELL! WELL! WELL! LOOK WHO'S HERE!")) {
                stopMusic = false;
                phase1.start();
                curPhase = 6;
            }

            // F6 music handler
            if (floorsix.hasSongs() && message.startsWith("[BOSS] Sadan: So you made it all the way here")) {
                floorsix.start();
                f6dungeonbossmusicstopperhehelol = true;
            } else if (floorsix.hasSongs() && message.startsWith("[BOSS] Sadan: NOOOOOOOOO!!!")) {
                floorsix.stop();
                if (ModConfig.jinglef6Music && jingle_f6_win.hasSongs()) {
                    jingle_f6_win.start();
                }
                f6dungeonbossmusicstopperhehelol = false;
            } else if (floorsix.hasSongs() && message.startsWith("[BOSS] Sadan: It was inevitable")) {
                floorsix.stop();
                if (ModConfig.jinglef6Music && jingle_f6_lose.hasSongs()) {
                    jingle_f6_lose.start();
                }
                f6dungeonbossmusicstopperhehelol = false;
            }

            //if (message.contains(":")) return;
            // ^^ the stupidest line of code ever (made me waste like 6 hours trying to find out whats wrong)

            //Stop all F7 music & Blood Room music at the end for some reason
            if (message.contains("EXTRA STATS ")) {
                curPhase = -1; // force no play
                dungeonboss.stop();
                bloodroom.stop();
                dungeon.stop();
                phase1.stop();
                phase2.stop();
                phase3.stop();
                phase4.stop();
                phase5.stop();
                stopMusic = true;
            } else if (message.contains("The BLOOD DOOR has been opened!")) {
                dungeon.stop();
                if (ModConfig.bloodRoomMusic) bloodroom.start();
            } else if (message.startsWith("[BOSS] The Watcher: You have proven yourself.")) {
                bloodroom.stop();
                if (ModConfig.bloodRoomMusic) bloodroomend.start();
            }

            //F7 end jingle handler
            if (ModConfig.jinglef7Music) {

                if (jingle_f7_win.hasSongs() && message.startsWith("[BOSS] Necron: I understand your words now, my master.")) {
                    jingle_f7_win.start();   
                    jingle7 = 2;
                } else if (jingle_f7_lose.hasSongs() && message.startsWith("[BOSS] ") && message.endsWith("FINALLY! This took way too long.")) {
                     jingle_f7_lose.start();
                     jingle7 = 1;
                 }

            }
        }

        //}

        //Season of Jerry music handler
        if (isInJerrysWorkshop) {
            if (message.contains("DEFEND JERRY'S WORKSHOP - WAVE")) { 
                jerryw.stop();
                jerryprefight.start();
                currentlyFightingCubes = true;
                canPlayJerryMusic = false;
                //stop the jerry's workshop music when wave is about to start
            } else if (message.endsWith("Mount Jerry has erupted! Defend the village from the fire monsters!")){ 
                jerryprefight.stop();
                jerryfight.start();
                currentlyFightingCubes = true;
                canPlayJerryMusic = false;
            } else if (message.contains("Collect your Gifts at the Gift piles!")) {
                jerryfight.stop(); // stop the jerry fight music
                isFightingCubes = false; // no longer fighting cubes
                canPlayJerryMusic = false;
                if (ModConfig.jingleJerryMusic) {
                    if (jinglej == 1) { 
                        jingle_jerry_win.start();
                    } else if (jinglej == 2) {
                        jingle_jerry_lose.start();
                    }
                }
                canCountTicks = true; //start counting down to play the jerry workshop music again
                ticksRemaining = 15;
            }

            //Setting the jingle variable to play the corresponding jingle (a little useless but it works)
            if (message.contains("YOU WON!")) {
                jinglej = 1;
            } else if (message.contains("YOU LOST!")) {
                jinglej = 2;
            }
        
        }

            
        
    }

    @SubscribeEvent
    public void onSound(PlaySoundEvent event) {
        if (ModConfig.disableHypixelMusic && cancelNotes && event.name.startsWith("note.")) {
            event.result = null;
        }
    }

    @SubscribeEvent
    public void postConfigInit(PostConfigInitEvent event) {
        init(event.configDirectory);
    }

    public static void init(String configDirectory) {
        if (configDirectory == null) return;
        File directory = new File(configDirectory + "/dsmmusic");
        if (!directory.exists()) directory.mkdir();

        reset();

        dungeonboss = new Song(directory, "dungeonboss", ModConfig.dungeonBossVolume);
        bloodroom = new Song(directory, "bloodroom", ModConfig.bloodRoomVolume);
        dungeon = new Song(directory, "dungeon", ModConfig.dungeonVolume);
        floorsix = new Song(directory, "floorsix", ModConfig.floorSixVolume);
        phase1 = new Song(directory, "phaseone", ModConfig.phase1Volume);
        phase2 = new Song(directory, "phasetwo", ModConfig.phase2Volume);
        phase3 = new Song(directory, "phasethree", ModConfig.phase3Volume);
        phase4 = new Song(directory, "phasefour", ModConfig.phase4Volume);
        phase5 = new Song(directory, "phasefive", ModConfig.phase5Volume);
        hub = new Song(directory, "hub", ModConfig.hubVolume);
        island = new Song(directory, "island", ModConfig.islandVolume);
        dungeonHub = new Song(directory, "dungeonhub", ModConfig.dungeonHubVolume);
        farmingIslands = new Song(directory, "farmingislands", ModConfig.farmingIslandsVolume);
        garden = new Song(directory, "garden", ModConfig.gardenVolume);
        goldMine = new Song(directory, "goldmine", ModConfig.goldMineVolume);
        deepCaverns = new Song(directory, "deepcaverns", ModConfig.deepCavernsVolume);
        dwarvenMines = new Song(directory, "dwarvenmines", ModConfig.dwarvenMinesVolume);
        crystalHollows = new Song(directory, "crystalhollows", ModConfig.crystalHollowsVolume);
        spidersDen = new Song(directory, "spidersden", ModConfig.spidersDenVolume);
        crimsonIsle = new Song(directory, "crimsonisle", ModConfig.crimsonIsleVolume);
        kuudra = new Song(directory, "kuudra", ModConfig.kuudraVolume);
        end = new Song(directory, "end", ModConfig.endVolume);
        park = new Song(directory, "park", ModConfig.parkVolume);
        jerryw = new Song(directory, "jerryworkshop", ModConfig.jerryVolume);
        jerryfight = new Song(directory, "jerryfight", ModConfig.jerryFightVolume);
        jerryprefight = new Song(directory, "jerry_prefight", ModConfig.jerryFightVolume);
        bloodroomend = new Song(directory, "bloodroomend", ModConfig.bloodRoomVolume);

        jingle_f7_win = new Song(directory, "jinglefloorsevenwin", ModConfig.jinglef7Volume);
        jingle_f7_lose = new Song(directory, "jinglefloorsevenlose", ModConfig.jinglef7Volume);
        jingle_f6_win = new Song(directory, "jinglefloorsixwin", ModConfig.jinglef6Volume);
        jingle_f6_lose = new Song(directory, "jinglefloorsixlose", ModConfig.jinglef6Volume);
        jingle_jerry_win = new Song(directory, "jinglejerrywin", ModConfig.jingleJerryVolume);
        jingle_jerry_lose = new Song(directory, "jinglejerrylose", ModConfig.jingleJerryVolume);

        dojo = new Song(directory, "dojo", ModConfig.crimsonIsleVolume);
        dojostart = new Song(directory, "dojo_start", ModConfig.crimsonIsleVolume);
    }

    public static void reset() {
        if (dungeonboss != null) dungeonboss.stop();
        if (bloodroom != null) bloodroom.stop();
        if (dungeon != null) dungeon.stop();
        if (floorsix != null) floorsix.stop();
        if (phase1 != null) phase1.stop();
        if (phase2 != null) phase2.stop();
        if (phase3 != null) phase3.stop();
        if (phase4 != null) phase4.stop();
        if (phase5 != null) phase5.stop();
        if (hub != null) hub.stop();
        if (island != null) island.stop();
        if (dungeonHub != null) dungeonHub.stop();
        if (farmingIslands != null) farmingIslands.stop();
        if (garden != null) garden.stop();
        if (goldMine != null) goldMine.stop();
        if (deepCaverns != null) deepCaverns.stop();
        if (dwarvenMines != null) dwarvenMines.stop();
        if (crystalHollows != null) crystalHollows.stop();
        if (spidersDen != null) spidersDen.stop();
        if (crimsonIsle != null) crimsonIsle.stop();
        if (kuudra != null) kuudra.stop();
        if (end != null) end.stop();
        if (park != null) park.stop();
        if (jerryw != null) jerryw.stop();
        if (jerryfight != null) jerryfight.stop();
        if (jerryprefight != null) jerryprefight.stop();
        if (bloodroomend != null) bloodroomend.stop();

        if (jingle_f7_lose != null) jingle_f7_lose.stop();
        if (jingle_f7_win != null) jingle_f7_win.stop();
        if (jingle_f6_lose != null) jingle_f6_lose.stop();
        if (jingle_f6_win != null) jingle_f6_win.stop();
        if (jingle_jerry_lose != null) jingle_jerry_lose.stop();
        if (jingle_jerry_win != null) jingle_jerry_win.stop();

        if (dojo != null) dojo.stop();
        if (dojostart != null) dojostart.stop();

        curPhase = 0;
        jinglej = 0;
        jingle7 = 0;

        stopMusic = true;

        isFightingCubes = false;
        currentlyFightingCubes = false;
        canPlayJerryMusic = true;
        canCountTicks = false;
        isInJerrysWorkshop = false;

        f6dungeonbossmusicstopperhehelol = false; //reminder hehe

        hasPlayedIntro = false;
        loopable = false;

        ticksRemaining = 0;

        
    }

    public static class Song {

        public Clip music;
        private final List<File> playlist = new ArrayList<>();
        private int volume;

        //private boolean isFading = false;
        //private int fadeDurationTicks = 0;
        //private int fadeElapsedTicks = 0;
        //private int fadeStartVolume = 100;
        //private int fadeTargetVolume = 100;
        //private String fadeEasing = "linear"; // "linear", "ease-in", "ease-out"

        public Song(File directory, String songName, int volume) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (!file.isDirectory() && file.getName().matches(songName + "\\d*(?:\\.wav)?\\.wav")) { // .wav.wav moment
                        playlist.add(file);
                        System.out.println("Added " + file.getName() + " to " + songName + " playlist.");
                    }
                }
            }

            this.volume = volume;
        }

        public void start() throws UnsupportedAudioFileException, LineUnavailableException, IOException {

            try {
                if (music == null) music = AudioSystem.getClip();
                if (!music.isRunning()) {
                    reset();
                    shuffle();
                    setVolume(volume);
                    cancelNotes = true;
                    music.setMicrosecondPosition(0);
                    music.start();
                }
            } catch (UnsupportedAudioFileException ex) {
                ex.printStackTrace();

                EntityPlayer player = Minecraft.getMinecraft().thePlayer;
                if (player != null) {
                    player.addChatMessage(new ChatComponentText(ModConfig.getColour(ModConfig.errorColour) + "Attempted to play non .wav file. Please use a .wav converter instead of renaming the file."));
                }
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();

                EntityPlayer player = Minecraft.getMinecraft().thePlayer;
                if (player != null) {
                    player.addChatMessage(new ChatComponentText(ModConfig.getColour(ModConfig.errorColour) + "Could not play music. Are your audio drivers up to date?"));
                }
            }
        }

        public void stop() {
            cancelNotes = false;
            if (music != null) {
                music.stop();
                if (music.isOpen()) music.close();
            }
        }

        public void shuffle() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
            if (playlist.size() > 0) {
                File file = playlist.get(new Random().nextInt(playlist.size()));
                music = AudioSystem.getClip();
                AudioInputStream ais = AudioSystem.getAudioInputStream(file);
                music.open(ais);
            }
        }

        public boolean setVolume(int volume) {
            this.volume = volume;
            if (playlist.size() < 1) return false;
            if (volume <= 0 || volume > 100) {
                EntityPlayer player = Minecraft.getMinecraft().thePlayer;
                if (player != null) player.addChatMessage(new ChatComponentText(ModConfig.getColour(ModConfig.errorColour) + "Volume can only be set between 0% and 100%."));
                return false;
            }

            if (music != null) {
                float decibels = (float) (20 * Math.log10(volume / 100.0));
                FloatControl control = (FloatControl) music.getControl(FloatControl.Type.MASTER_GAIN);
                if (decibels <= control.getMinimum() || decibels >= control.getMaximum()) return false;
                control.setValue(decibels);
            }

            return true;
        }

        public boolean hasSongs() {
            return playlist.size() > 0;
        }

        // fade in and outs, will finish later
        //public void fadeTo(int targetVolume, int durationTicks, String easing) {
        //    if (music == null || !music.isOpen()) return;
        //
        //    fadeStartVolume = this.volume;
        //    fadeTargetVolume = targetVolume;
        //    fadeDurationTicks = durationTicks;
        //    fadeElapsedTicks = 0;
        //}

        //public void tickFade() {
        //    if (!isFading) return;
        //
        //    fadeElapsedTicks++;
        //    float t = (float) fadeElapsedTicks / fadeDurationTicks;
        //    if (t >= 1.0f) {
        //        setVolume(fadeTargetVolume);
        //        isFading = false;
        //        return;
        //    }
        //
        //    float easedT = applyEasing(t, fadeEasing);
        //    int currentVolume = (int) (fadeStartVolume + (fadeTargetVolume - fadeStartVolume) * easedT);
        //    setVolume(currentVolume);
        //}

        //private float applyEasing(float t, String type) {
        //    switch (type) {
        //        case "ease-in":
        //            return t * t;
        //        case "ease-out":
        //            return (float) (1 - Math.pow(1 - t, 2));
        //        case "ease-in-out":
        //            return (float) (t < 0.5 ? 2 * t * t : 1 - Math.pow(-2 * t + 2, 2) / 2);
        //        default: // linear
        //            return t;
        //    }
        //}
    }

}
