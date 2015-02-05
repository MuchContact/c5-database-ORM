package org.nightschool.dao;

import org.nightschool.model.Disk;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thoughtworks on 12/26/14.
 */
@Deprecated
public class DiskDao {
    ArrayList<Disk> disks = new ArrayList<Disk>();

    public DiskDao() {
        disks.add(new Disk(1,"小清新光盘", "../images/disk/fancy-disk.jpg", "小清新、小文艺 35元/10张", 3.5, 3.5, 100, "twer"));
        disks.add(new Disk(2,"婚庆光盘", "../images/disk/marriage-disk.jpg", "记录你的美好瞬间 50元/10张", 5.0, 5.0,100, "twer"));
        disks.add(new Disk(3,"1TB大容量光盘", "../images/disk/1TB-disk.jpg", "解放你的硬盘  100元/10张", 10.0, 10.0,100, "twer"));
    }

    public List<Disk> listDisks() {
        return disks;
    }

    public void add(Disk disk) {
        disks.add(disk);
    }

    public void remove(int index) {
        disks.remove(index);
    }
}
