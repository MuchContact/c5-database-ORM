package org.nightschool.controller;

import org.nightschool.dao.DiskDao;
import org.nightschool.mapper.DiskMapper;
import org.nightschool.model.Disk;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

import static org.nightschool.wrapper.MybatisWrapper.getMapper;

/**
 * Created by Thoughtworks on 12/20/14.
 */
@Path("/disks")
@Produces(MediaType.APPLICATION_JSON)
public class DiskController {
    //    private final ArrayList<Disk> disks = new ArrayList<>();
    private final DiskDao diskDao = new DiskDao();

    @GET
    public List<Disk> list() {
        return diskDao.listDisks();
    }

    @POST
    @Path("add")
    @Consumes(MediaType.APPLICATION_JSON)
    public void add(Disk disk) {
//        diskDao.add(disk);
        DiskMapper mapper = getMapper(DiskMapper.class);
        mapper.insert(disk);
    }

    @DELETE
    @Path("remove/{id}")
    public void remove(@PathParam("id") int index) {
        diskDao.remove(index);
    }
}
