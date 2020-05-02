package com.example.hapticfeedback.ui.util;

import java.util.ArrayList;

public class util {

    //Method to register if a pin has been selected or not
    private static int coilCounters = 0;

    public static int getCoilCounter() {
        return coilCounters;
    }

    public void setCoilCounter(int coilCounters) {
        this.coilCounters = coilCounters;
    }

    //Empty string to allow for padding inside commandInformation so you can edit on fly
    private String emptys = "";

    public String getEmpty() {
        return emptys;
    }

    public void setEmpty(String emptys) {
        this.emptys = emptys;
    }

    //Overall pin selection data
    private ArrayList<Object> pinSelections = new ArrayList<>();

    public ArrayList<Object> getPinSelection() {
        return pinSelections;
    }

    public void setPinSelection(ArrayList<Object> pinSelections) {
        this.pinSelections = pinSelections;
    }

    //Overall command data for frames
    private ArrayList<Object> commandInformations = new ArrayList<>();

    public void setCommandInformation(ArrayList<Object> commandInformations) {
        this.commandInformations = commandInformations;
    }

    public ArrayList<Object> getCommandInformation() {
        return commandInformations;
    }

    //Command data for verification when saving a frame
    private ArrayList<Object> fNames = new ArrayList<>();

    public ArrayList<Object> getfName() {
        return fNames;
    }

    public void setfName(ArrayList<Object> fNames) {
        this.fNames = fNames;
    }

    private ArrayList<Object> fCoils = new ArrayList<>();

    public ArrayList<Object> getfCoil() {
        return fCoils;
    }

    public void setfCoil(ArrayList<Object> fCoils) {
        this.fCoils = fCoils;
    }

    private ArrayList<Object> fDelays = new ArrayList<>();

    public ArrayList<Object> getfDelay() {
        return fDelays;
    }

    public void setfDelay(ArrayList<Object> fDelays) {
        this.fDelays = fDelays;
    }

    private ArrayList<Object> fActivations = new ArrayList<>();

    public ArrayList<Object> getfActivation() {
        return fActivations;
    }

    public void setfActivation(ArrayList<Object> fActivations) {
        this.fActivations = fActivations;
    }

    //Command data for verification when saving a frame
    private ArrayList<Object> sNames = new ArrayList<>();

    public ArrayList<Object> getsName() {
        return sNames;
    }

    public void setsName(ArrayList<Object> sNames) {
        this.sNames = sNames;
    }

    private ArrayList<Object> sFrame = new ArrayList<>();

    public ArrayList<Object> getsFrames() {
        return sFrame;
    }

    public void setsFrames(ArrayList<Object> sFrame) {
        this.sFrame = sFrame;
    }

    //Loaded frame verification
    private ArrayList<Object> lNames = new ArrayList<>();

    public ArrayList<Object> getlName() {
        return lNames;
    }

    public void setlName(ArrayList<Object> lNames) {
        this.lNames = lNames;
    }

    //Coils used data
    private ArrayList<Object> coilsUseds = new ArrayList<>();

    public ArrayList<Object> getCoilsUsed() {
        return coilsUseds;
    }

    public void setCoilsUsed(ArrayList<Object> coilsUseds) {
        this.coilsUseds = coilsUseds;
    }

    //Total amount of coils
    private ArrayList<Integer> total = new ArrayList<>();

    public ArrayList<Integer> getTotal() {
        return total;
    }

    public void setTotal(ArrayList<Integer> total) {
        this.total = total;
    }

    //Total amount of frames
    private ArrayList<Integer> totalFrame = new ArrayList<>();

    public ArrayList<Integer> getTotalFrames() {
        return totalFrame;
    }

    public void setTotalFrames(ArrayList<Integer> totalFrame) {
        this.totalFrame = totalFrame;
    }

    //Overall command data for sequences
    private ArrayList<Object> commandInformationSeqs = new ArrayList<>();

    public ArrayList<Object> getCommandInformationSeq() {
        return commandInformationSeqs;
    }

    public void setCommandInformationSeq(ArrayList<Object> commandInformationSeqs) {
        this.commandInformationSeqs = commandInformationSeqs;
    }

    //Frames used data
    private ArrayList<Object> framesUseds = new ArrayList<>();

    public ArrayList<Object> getFramesUsed() {
        return framesUseds;
    }

    public void setFramesUsed(ArrayList<Object> framesUseds) {
        this.framesUseds = framesUseds;
    }

    //FrameSelectionData
    private ArrayList<Object> frameSelection = new ArrayList<>();

    public ArrayList<Object> getFrameSelections() {
        return frameSelection;
    }

    public void setFrameSelections(ArrayList<Object> frameSelection) {
        this.frameSelection = frameSelection;
    }

    //Finished frames
    private ArrayList<Object> frameFinished1s = new ArrayList<>();

    public ArrayList<Object> getFrameFinished1() {
        return frameFinished1s;
    }

    public void setFrameFinished1(ArrayList<Object> frameFinished1s) {
        this.frameFinished1s = frameFinished1s;
    }

    private ArrayList<Object> frameFinished2s = new ArrayList<>();

    public ArrayList<Object> getFrameFinished2() {
        return frameFinished2s;
    }

    public void setFrameFinished2(ArrayList<Object> frameFinished2s) {
        this.frameFinished2s = frameFinished2s;
    }

    private ArrayList<Object> frameFinished3s = new ArrayList<>();

    public ArrayList<Object> getFrameFinished3() {
        return frameFinished3s;
    }

    public void setFrameFinished3(ArrayList<Object> frameFinished3s) {
        this.frameFinished3s = frameFinished3s;
    }

    private ArrayList<Object> frameFinished4s = new ArrayList<>();

    public ArrayList<Object> getFrameFinished4() {
        return frameFinished4s;
    }

    public void setFrameFinished4(ArrayList<Object> frameFinished3s) {
        this.frameFinished4s = frameFinished4s;
    }

    private ArrayList<Object> frameFinished5s = new ArrayList<>();

    public ArrayList<Object> getFrameFinished5() {
        return frameFinished5s;
    }

    public void setFrameFinished5(ArrayList<Object> frameFinished5s) {
        this.frameFinished5s = frameFinished5s;
    }

    private ArrayList<Object> frameFinished6s = new ArrayList<>();

    public ArrayList<Object> getFrameFinished6() {
        return frameFinished6s;
    }

    public void setFrameFinished6(ArrayList<Object> frameFinished6s) {
        this.frameFinished6s = frameFinished6s;
    }

    //Frames loaded
    private ArrayList<Object> loadedFrame1s = new ArrayList<>();

    public ArrayList<Object> getLoadedFrame1() {
        return loadedFrame1s;
    }

    public void setLoadedFrame1(ArrayList<Object> loadedFrame1s) {
        this.loadedFrame1s = loadedFrame1s;
    }

    private ArrayList<Object> loadedFrame2s = new ArrayList<>();

    public ArrayList<Object> getLoadedFrame2() {
        return loadedFrame2s;
    }

    public void setLoadedFrame2(ArrayList<Object> loadedFrame2s) {
        this.loadedFrame2s = loadedFrame2s;
    }

    private ArrayList<Object> loadedFrame3s = new ArrayList<>();

    public ArrayList<Object> getLoadedFrame3() {
        return loadedFrame3s;
    }

    public void setLoadedFrame3(ArrayList<Object> loadedFrame3s) {
        this.loadedFrame3s = loadedFrame3s;
    }

    private ArrayList<Object> loadedFrame4s = new ArrayList<>();

    public ArrayList<Object> getLoadedFrame4() {
        return loadedFrame4s;
    }

    public void setLoadedFrame4(ArrayList<Object> loadedFrame4s) {
        this.loadedFrame4s = loadedFrame4s;
    }

    private ArrayList<Object> loadedFrame5s = new ArrayList<>();

    public ArrayList<Object> getLoadedFrame5() {
        return loadedFrame5s;
    }

    public void setLoadedFrame5(ArrayList<Object> loadedFrame5s) {
        this.loadedFrame5s = loadedFrame5s;
    }

    private ArrayList<Object> loadedFrame6s = new ArrayList<>();

    public ArrayList<Object> getLoadedFrame6() {
        return loadedFrame6s;
    }

    public void setLoadedFrame6(ArrayList<Object> loadedFrame6s) {
        this.loadedFrame6s = loadedFrame6s;
    }

    //Frames loaded name
    private ArrayList<Object> loadedFrame1Names = new ArrayList<>();

    public ArrayList<Object> getLoadedFrame1Name() {
        return loadedFrame1Names;
    }

    public void setLoadedFrame1Name(ArrayList<Object> loadedFrame1Names) {
        this.loadedFrame1Names = loadedFrame1Names;
    }

    private ArrayList<Object> loadedFrame2Names = new ArrayList<>();

    public ArrayList<Object> getLoadedFrame2Name() {
        return loadedFrame2Names;
    }

    public void setLoadedFrame2Name(ArrayList<Object> loadedFrame2Names) {
        this.loadedFrame2Names = loadedFrame2Names;
    }

    private ArrayList<Object> loadedFrame3Names = new ArrayList<>();

    public ArrayList<Object> getLoadedFrame3Name() {
        return loadedFrame3Names;
    }

    public void setLoadedFrame3Name(ArrayList<Object> loadedFrame3Names) {
        this.loadedFrame3Names = loadedFrame3Names;
    }

    private ArrayList<Object> loadedFrame4Names = new ArrayList<>();

    public ArrayList<Object> getLoadedFrame4Name() {
        return loadedFrame4Names;
    }

    public void setLoadedFrame4Name(ArrayList<Object> loadedFrame4Names) {
        this.loadedFrame4Names = loadedFrame4Names;
    }

    private ArrayList<Object> loadedFrame5Names = new ArrayList<>();

    public ArrayList<Object> getLoadedFrame5Name() {
        return loadedFrame5Names;
    }

    public void setLoadedFrame5Name(ArrayList<Object> loadedFrame5Names) {
        this.loadedFrame5Names = loadedFrame5Names;
    }

    private ArrayList<Object> loadedFrame6Names = new ArrayList<>();

    public ArrayList<Object> getLoadedFrame6Name() {
        return loadedFrame6Names;
    }

    public void setLoadedFrame6Name(ArrayList<Object> loadedFrame6Names) {
        this.loadedFrame6Names = loadedFrame6Names;
    }

    //Edited frame names with $f
    private ArrayList<Object> loadedFrameNameF1s = new ArrayList<>();

    public ArrayList<Object> getLoadedFrameNameF1() {
        return loadedFrameNameF1s;
    }

    public void setLoadedFrameNameF1(ArrayList<Object> loadedFrameNameF1s) {
        this.loadedFrameNameF1s = loadedFrameNameF1s;
    }

    private ArrayList<Object> loadedFrameNameF2s = new ArrayList<>();

    public ArrayList<Object> getLoadedFrameNameF2() {
        return loadedFrameNameF2s;
    }

    public void setLoadedFrameNameF2(ArrayList<Object> loadedFrameNameF2s) {
        this.loadedFrameNameF2s = loadedFrameNameF2s;
    }

    private ArrayList<Object> loadedFrameNameF3s = new ArrayList<>();

    public ArrayList<Object> getLoadedFrameNameF3() {
        return loadedFrameNameF3s;
    }

    public void setLoadedFrameNameF3(ArrayList<Object> loadedFrameNameF3s) {
        this.loadedFrameNameF3s = loadedFrameNameF3s;
    }

    private ArrayList<Object> loadedFrameNameF4s = new ArrayList<>();

    public ArrayList<Object> getLoadedFrameNameF4() {
        return loadedFrameNameF4s;
    }

    public void setLoadedFrameNameF4(ArrayList<Object> loadedFrameNameF4s) {
        this.loadedFrameNameF4s = loadedFrameNameF4s;
    }

    private ArrayList<Object> loadedFrameNameF5s = new ArrayList<>();

    public ArrayList<Object> getLoadedFrameNameF5() {
        return loadedFrameNameF5s;
    }

    public void setLoadedFrameNameF5(ArrayList<Object> loadedFrameNameF5s) {
        this.loadedFrameNameF5s = loadedFrameNameF5s;
    }

    private ArrayList<Object> loadedFrameNameF6s = new ArrayList<>();

    public ArrayList<Object> getLoadedFrameNameF6() {
        return loadedFrameNameF6s;
    }

    public void setLoadedFrameNameF6(ArrayList<Object> loadedFrameNameF6s) {
        this.loadedFrameNameF6s = loadedFrameNameF6s;
    }

    //Finished loaded frames
    private ArrayList<Object> loadedFrameFinished1s = new ArrayList<>();

    public ArrayList<Object> getLoadedFrameFinished1() {
        return loadedFrameFinished1s;
    }

    public void setLoadedFrameFinished1(ArrayList<Object> loadedFrameFinished1s) {
        this.loadedFrameFinished1s = loadedFrameFinished1s;
    }

    private ArrayList<Object> loadedFrameFinished2s = new ArrayList<>();

    public ArrayList<Object> getLoadedFrameFinished2() {
        return loadedFrameFinished2s;
    }

    public void setLoadedFrameFinished2(ArrayList<Object> loadedFrameFinished2s) {
        this.loadedFrameFinished2s = loadedFrameFinished2s;
    }

    private ArrayList<Object> loadedFrameFinished3s = new ArrayList<>();

    public ArrayList<Object> getLoadedFrameFinished3() {
        return loadedFrameFinished3s;
    }

    public void setLoadedFrameFinished3(ArrayList<Object> loadedFrameFinished3s) {
        this.loadedFrameFinished3s = loadedFrameFinished3s;
    }

    private ArrayList<Object> loadedFrameFinished4s = new ArrayList<>();

    public ArrayList<Object> getLoadedFrameFinished4() {
        return loadedFrameFinished4s;
    }

    public void setLoadedFrameFinished4(ArrayList<Object> loadedFrameFinished4s) {
        this.loadedFrameFinished4s = loadedFrameFinished4s;
    }

    private ArrayList<Object> loadedFrameFinished5s = new ArrayList<>();

    public ArrayList<Object> getLoadedFrameFinished5() {
        return loadedFrameFinished5s;
    }

    public void setLoadedFrameFinished5(ArrayList<Object> loadedFrameFinished5s) {
        this.loadedFrameFinished5s = loadedFrameFinished5s;
    }

    private ArrayList<Object> loadedFrameFinished6s = new ArrayList<>();

    public ArrayList<Object> getLoadedFrameFinished6() {
        return loadedFrameFinished6s;
    }

    public void setLoadedFrameFinished6(ArrayList<Object> loadedFrameFinished6s) {
        this.loadedFrameFinished6s = loadedFrameFinished6s;
    }

    //Finished sequences
    private ArrayList<Object> sequenceFinished1s = new ArrayList<>();

    public ArrayList<Object> getSequenceFinished1() {
        return sequenceFinished1s;
    }

    public void setSequenceFinished1(ArrayList<Object> sequenceFinished1s) {
        this.sequenceFinished1s = sequenceFinished1s;
    }

    private ArrayList<Object> sequenceFinished2s = new ArrayList<>();

    public ArrayList<Object> getSequenceFinished2() {
        return sequenceFinished2s;
    }

    public void setSequenceFinished2(ArrayList<Object> sequenceFinished2s) {
        this.sequenceFinished2s = sequenceFinished2s;
    }

    private ArrayList<Object> sequenceFinished3s = new ArrayList<>();

    public ArrayList<Object> getSequenceFinished3() {
        return sequenceFinished3s;
    }

    public void setSequenceFinished3(ArrayList<Object> sequenceFinished3s) {
        this.sequenceFinished3s = sequenceFinished3s;
    }

    private ArrayList<Object> sequenceFinished4s = new ArrayList<>();

    public ArrayList<Object> getSequenceFinished4() {
        return sequenceFinished4s;
    }

    public void setSequenceFinished4(ArrayList<Object> sequenceFinished4s) {
        this.sequenceFinished4s = sequenceFinished4s;
    }

    //Finished edited sequences
    private ArrayList<Object> editedSequenceFinished1s = new ArrayList<>();

    public ArrayList<Object> getEditedSequenceFinished1() {
        return editedSequenceFinished1s;
    }

    public void setEditedSequenceFinished1(ArrayList<Object> editedSequenceFinished1s) {
        this.editedSequenceFinished1s = editedSequenceFinished1s;
    }

    private ArrayList<Object> editedSequenceFinished2s = new ArrayList<>();

    public ArrayList<Object> getEditedSequenceFinished2() {
        return editedSequenceFinished2s;
    }

    public void setEditedSequenceFinished2(ArrayList<Object> editedSequenceFinished2s) {
        this.editedSequenceFinished2s = editedSequenceFinished2s;
    }

    private ArrayList<Object> editedSequenceFinished3s = new ArrayList<>();

    public ArrayList<Object> getEditedSequenceFinished3() {
        return editedSequenceFinished3s;
    }

    public void setEditedSequenceFinished3(ArrayList<Object> editedSequenceFinished3s) {
        this.editedSequenceFinished3s = editedSequenceFinished3s;
    }

    private ArrayList<Object> editedSequenceFinished4s = new ArrayList<>();

    public ArrayList<Object> getEditedSequenceFinished4() {
        return editedSequenceFinished4s;
    }

    public void setEditedSequenceFinished4(ArrayList<Object> editedSequenceFinished4s) {
        this.editedSequenceFinished4s = editedSequenceFinished4s;
    }

    //Frame names with $F
    private ArrayList<Object> frameName1s = new ArrayList<>();

    public ArrayList<Object> getFrameName1() {
        return frameName1s;
    }

    public void setFrameName1(ArrayList<Object> frameName1s) {
        this.frameName1s = frameName1s;
    }

    private ArrayList<Object> frameName2s = new ArrayList<>();

    public ArrayList<Object> getFrameName2() {
        return frameName2s;
    }

    public void setFrameName2(ArrayList<Object> frameName2s) {
        this.frameName2s = frameName2s;
    }

    private ArrayList<Object> frameName3s = new ArrayList<>();

    public ArrayList<Object> getFrameName3() {
        return frameName3s;
    }

    public void setFrameName3(ArrayList<Object> frameName3s) {
        this.frameName3s = frameName3s;
    }

    private ArrayList<Object> frameName4s = new ArrayList<>();

    public ArrayList<Object> getFrameName4() {
        return frameName4s;
    }

    public void setFrameName4(ArrayList<Object> frameName4s) {
        this.frameName4s = frameName4s;
    }

    private ArrayList<Object> frameName5s = new ArrayList<>();

    public ArrayList<Object> getFrameName5() {
        return frameName5s;
    }

    public void setFrameName5(ArrayList<Object> frameName5s) {
        this.frameName5s = frameName5s;
    }

    private ArrayList<Object> frameName6s = new ArrayList<>();

    public ArrayList<Object> getFrameName6() {
        return frameName6s;
    }

    public void setFrameName6(ArrayList<Object> frameName6s) {
        this.frameName6s = frameName6s;
    }

    //Frame names with $f
    private ArrayList<Object> frameNameF1s = new ArrayList<>();

    public ArrayList<Object> getFrameNameF1() {
        return frameNameF1s;
    }

    public void setFrameNameF1(ArrayList<Object> frameNameF1s) {
        this.frameNameF1s = frameNameF1s;
    }

    private ArrayList<Object> frameNameF2s = new ArrayList<>();

    public ArrayList<Object> getFrameNameF2() {
        return frameNameF2s;
    }

    public void setFrameNameF2(ArrayList<Object> frameNameF2s) {
        this.frameNameF2s = frameNameF2s;
    }

    private ArrayList<Object> frameNameF3s = new ArrayList<>();

    public ArrayList<Object> getFrameNameF3() {
        return frameNameF3s;
    }

    public void setFrameNameF3(ArrayList<Object> frameNameF3s) {
        this.frameNameF3s = frameNameF3s;
    }

    private ArrayList<Object> frameNameF4s = new ArrayList<>();

    public ArrayList<Object> getFrameNameF4() {
        return frameNameF4s;
    }

    public void setFrameNameF4(ArrayList<Object> frameNameF4s) {
        this.frameNameF4s = frameNameF4s;
    }

    private ArrayList<Object> frameNameF5s = new ArrayList<>();

    public ArrayList<Object> getFrameNameF5() {
        return frameNameF5s;
    }

    public void setFrameNameF5(ArrayList<Object> frameNameF5s) {
        this.frameNameF5s = frameNameF5s;
    }

    private ArrayList<Object> frameNameF6s = new ArrayList<>();

    public ArrayList<Object> getFrameNameF6() {
        return frameNameF6s;
    }

    public void setFrameNameF6(ArrayList<Object> frameNameF6s) {
        this.frameNameF6s = frameNameF6s;
    }

    //Sequence names
    private ArrayList<Object> sequenceName1s = new ArrayList<>();

    public ArrayList<Object> getSequenceName1() {
        return sequenceName1s;
    }

    public void setSequenceName1(ArrayList<Object> sequenceName1s) {
        this.sequenceName1s = sequenceName1s;
    }

    private ArrayList<Object> sequenceName2s = new ArrayList<>();

    public ArrayList<Object> getSequenceName2() {
        return sequenceName2s;
    }

    public void setSequenceName2(ArrayList<Object> sequenceName2s) {
        this.sequenceName2s = sequenceName2s;
    }

    private ArrayList<Object> sequenceName3s = new ArrayList<>();

    public ArrayList<Object> getSequenceName3() {
        return sequenceName3s;
    }

    public void setSequenceName3(ArrayList<Object> sequenceName3s) {
        this.sequenceName3s = sequenceName3s;
    }

    private ArrayList<Object> sequenceName4s = new ArrayList<>();

    public ArrayList<Object> getSequenceName4() {
        return sequenceName4s;
    }

    public void setSequenceName4(ArrayList<Object> sequenceName4s) {
        this.sequenceName4s = sequenceName4s;
    }

    //Sequence edited names
    private ArrayList<Object> editedSequenceName1s = new ArrayList<>();

    public ArrayList<Object> getEditedSequenceName1() {
        return editedSequenceName1s;
    }

    public void setEditedSequenceName1(ArrayList<Object> editedSequenceName1s) {
        this.editedSequenceName1s = editedSequenceName1s;
    }

    private ArrayList<Object> editedSequenceName2s = new ArrayList<>();

    public ArrayList<Object> getEditedSequenceName2() {
        return editedSequenceName2s;
    }

    public void setEditedSequenceName2(ArrayList<Object> editedSequenceName2s) {
        this.editedSequenceName2s = editedSequenceName2s;
    }

    private ArrayList<Object> editedSequenceName3s = new ArrayList<>();

    public ArrayList<Object> getEditedSequenceName3() {
        return editedSequenceName3s;
    }

    public void setEditedSequenceName3(ArrayList<Object> editedSequenceName3s) {
        this.editedSequenceName3s = editedSequenceName3s;
    }

    private ArrayList<Object> editedSequenceName4s = new ArrayList<>();

    public ArrayList<Object> getEditedSequenceName4() {
        return editedSequenceName4s;
    }

    public void setEditedSequenceName4(ArrayList<Object> editedSequenceName4s) {
        this.editedSequenceName4s = editedSequenceName4s;
    }

    //Boolean operators for name verification
    private boolean firstFrames = false;

    public boolean getFirstFrame() {
        return firstFrames;
    }

    public void setFirstFrame(boolean firstFrames) {
        this.firstFrames = firstFrames;
    }

    private boolean secondFrames = false;

    public boolean getSecondFrame() {
        return secondFrames;
    }

    public void setSecondFrame(boolean secondFrames) {
        this.secondFrames = secondFrames;
    }

    private boolean thirdFrames = false;

    public boolean getThirdFrame() {
        return thirdFrames;
    }

    public void setThirdFrame(boolean thirdFrames) {
        this.thirdFrames = thirdFrames;
    }

    private boolean fourthFrames = false;

    public boolean getFourthFrame() {
        return fourthFrames;
    }

    public void setFourthFrame(boolean fourthFrames) {
        this.fourthFrames = fourthFrames;
    }

    private boolean fifthFrames = false;

    public boolean getFifthFrame() {
        return fifthFrames;
    }

    public void setFifthFrame(boolean fifthFrames) {
        this.fifthFrames = fifthFrames;
    }

    private boolean sixthFrames = false;

    public boolean getSixthFrame() {
        return sixthFrames;
    }

    public void setSixthFrame(boolean sixthFrames) {
        this.sixthFrames = sixthFrames;
    }

    private boolean firstSequences = false;

    public boolean getFirstSequence() {
        return firstSequences;
    }

    public void setFrameFinished1(boolean firstSequences) {
        this.firstSequences = firstSequences;
    }

    private boolean secondSequences = false;

    public boolean getSecondSequence() {
        return secondSequences;
    }

    public void setSecondSequence(boolean secondSequences) {
        this.secondSequences = secondSequences;
    }

    private boolean thirdSequences = false;

    public boolean getThirdSequence() {
        return thirdSequences;
    }

    public void setThirdSequence(boolean thirdSequences) {
        this.thirdSequences = thirdSequences;
    }

    private boolean fourthSequences = false;

    public boolean getFourthSequence() {
        return fourthSequences;
    }

    public void setFourthSequence(boolean fourthSequences) {
        this.fourthSequences = fourthSequences;
    }

    //Boolean operators for user and task verification
    private boolean firstUserBools = false;

    public boolean getFirstUserBool() {
        return firstUserBools;
    }

    public void setFirstUserBool(boolean firstUserBools) {
        this.firstUserBools = firstUserBools;
    }

    private boolean secondUserBools = false;

    public boolean getSecondUserBool() {
        return secondUserBools;
    }

    public void setSecondUserBool(boolean secondUserBools) {
        this.secondUserBools = secondUserBools;
    }

    private boolean thirdUserBools = false;

    public boolean getThirdUserBool() {
        return thirdUserBools;
    }

    public void setThirdUserBool(boolean thirdUserBools) {
        this.thirdUserBools = thirdUserBools;
    }

    private boolean taskOnes = false;

    public boolean getTaskOne() {
        return taskOnes;
    }

    public void setTaskOne(boolean taskOnes) {
        this.taskOnes = taskOnes;
    }

    private boolean taskTwos = false;

    public boolean getTaskTwo() {
        return taskTwos;
    }

    public void setTaskTwo(boolean taskTwos) {
        this.taskTwos = taskTwos;
    }

    private boolean taskThrees = false;

    public boolean getTaskThree() {
        return taskThrees;
    }

    public void setTaskThree(boolean taskThrees) {
        this.taskThrees = taskThrees;
    }

    private boolean taskFours = false;

    public boolean getTaskFour() {
        return taskFours;
    }

    public void setTaskFour(boolean taskFours) {
        this.taskFours = taskFours;
    }

    //Loading a Frame
    private boolean loadeds = false;

    public boolean getLoaded() {
        return loadeds;
    }

    public void setLoaded(boolean loadeds) {
        this.loadeds = loadeds;
    }
}
