package com.sylxnc.nera.api.data;


import com.sylxnc.nera.api.data.license.LicenseType;
import com.sylxnc.nera.api.data.services.StorageService;
import lombok.Getter;
import net.dv8tion.jda.api.entities.Guild;
import org.bson.Document;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ServerData {

    private StorageService storage;
    private Document doc;

    @Getter
    private String serverID;
    @Getter
    private String serverName;
    @Getter
    private String ownerID;
    @Getter
    private LocalDateTime joinedDate;
    @Getter
    private int members;
    @Getter
    private LocalDateTime dataDeleteDate;
    @Getter
    private LicenseType licenseType;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private boolean existsInBD;

    public ServerData(String serverID){
        this.serverID = serverID;
        this.storage = new StorageService();
        this.storage.init();
        if(this.storage.document(serverID,"Servers") != null){
            this.existsInBD = true;
            this.doc = this.storage.document(serverID,"Servers");
        }else {
            this.doc = new Document("id_",serverID);
            this.existsInBD = false;
        }
    }

    public void initInDB(Guild guild){
        if(this.existsInBD) return;

        this.doc.append("Name",guild.getName());
        this.doc.append("OwnerID",guild.getOwner().getId());
        this.doc.append("Members",guild.getMembers().size());
        this.doc.append("Joined",formatter.format(LocalDateTime.now()));
        this.doc.append("DataDelete",formatter.format(LocalDateTime.now()));
        this.doc.append("License","NORMAL");
        this.storage.pushDoc(this.doc,"Servers");
    }

    public void updateInDB(Guild guild){
        if(!this.existsInBD) return;

        this.doc.replace("Name",guild.getName());
        this.doc.replace("OwnerID",guild.getOwner().getId());
        this.doc.replace("Members",guild.getMembers().size());
        this.doc.replace("DataDelete",formatter.format(LocalDateTime.now()));

        this.storage.pushDoc(this.doc,"Servers");
    }

    public void load(){
        if(existsInBD){
            this.serverName = this.doc.getString("Name");
            this.ownerID = this.doc.getString("OwnerID");
            this.joinedDate = LocalDateTime.parse(this.doc.getString("Joined"), formatter);
            this.members = this.doc.getInteger("Members");

            switch (this.doc.getString("License")){
                case "BETA":
                    this.licenseType = LicenseType.BETA;
                    break;
                case "DEVELOPMENT":
                    this.licenseType = LicenseType.DEVELOPMENT;
                    break;
                default:
                    this.licenseType = LicenseType.NORMAL;
                    break;
            }
        }
    }


}
