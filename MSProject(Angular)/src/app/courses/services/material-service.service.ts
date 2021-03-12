import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MaterialServiceService {

  constructor(private httpClient:HttpClient) { }

getMaterial(courseId)
{
  return this.httpClient.get("http://localhost:8090/material/getLatestMaterial/"+courseId);
}

getAllMaterial(courseId)
{
  return this.httpClient.get("http://localhost:8090/material/getAllMaterial/"+courseId);
}

updateMaterial(materialData)
{
  let materialInput=new FormData();
  materialInput.append('previousMaterialId',materialData.materialId)
  materialInput.append('courseId',materialData.courseId);
  materialInput.append('file',materialData.fileData)
  materialInput.append('materialDescription',materialData.materialDescription)

  return this.httpClient.put('http://localhost:8090/material/updateMaterial',materialInput);
}


getPreviousVersions(courseId,materialId)
{
  return this.httpClient.get("http://localhost:8090/material/getAllMaterial/"+courseId+"/"+materialId);
}


deleteService(materialId)
{
  return this.httpClient.delete("http://localhost:8090/material/deleteLatest/"+materialId);
}

addNewMaterial(courseId,materialDescription,file)
{
  let newMaterialInput=new FormData()
  newMaterialInput.append("courseId",courseId);
  newMaterialInput.append("materialDescription",materialDescription);
  newMaterialInput.append("file",file);

  return this.httpClient.post("http://localhost:8090/material/addMaterial",newMaterialInput);
}

}
