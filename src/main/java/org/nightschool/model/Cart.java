package org.nightschool.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;

public class Cart {

    private final ArrayList<Disk> disks = new ArrayList<>();

    public List<Disk> getDisks() {
        return disks;
    }

    public void addDisk(Disk disk) {
        disks.add(disk);
    }

    public int countKinds() {
        HashSet<Disk> diskSet = new HashSet<>();
        disks.forEach((disk) -> diskSet.add(disk));
        return diskSet.size();
    }

    public void removeDisk(Disk disk) {
        final int[] count = {0};
        disks.forEach((d) -> {
            if (d.equals(disk)) {
                count[0]++;
            }
        });
        //TODO remove using lambda?
        int i = 0;
        while (i < count[0]) {
            disks.remove(disk);
            i++;
        }
    }
}
