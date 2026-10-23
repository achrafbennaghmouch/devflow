import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Project, ProjectRequest, ProjectStatus, TaskItem, TaskRequest, TaskStatus } from './models';

@Injectable({providedIn:'root'})
export class ProjectService {
 private http=inject(HttpClient); private url='http://localhost:8080/api/projects';
 getAll(filters?:{name?:string;status?:ProjectStatus}):Observable<Project[]> {let p=new HttpParams();if(filters?.name)p=p.set('name',filters.name);if(filters?.status)p=p.set('status',filters.status);return this.http.get<Project[]>(this.url,{params:p});}
 create(data:ProjectRequest):Observable<Project>{return this.http.post<Project>(this.url,data);}
 update(id:number,data:ProjectRequest):Observable<Project>{return this.http.put<Project>(`${this.url}/${id}`,data);}
 delete(id:number):Observable<void>{return this.http.delete<void>(`${this.url}/${id}`);}
}
@Injectable({providedIn:'root'})
export class TaskService {
 private http=inject(HttpClient); private url='http://localhost:8080/api/tasks';
 getAll(filters?:{projectId?:number;status?:TaskStatus}):Observable<TaskItem[]> {let p=new HttpParams();if(filters?.projectId!==undefined)p=p.set('projectId',filters.projectId);if(filters?.status)p=p.set('status',filters.status);return this.http.get<TaskItem[]>(this.url,{params:p});}
 create(data:TaskRequest):Observable<TaskItem>{return this.http.post<TaskItem>(this.url,data);}
 update(id:number,data:TaskRequest):Observable<TaskItem>{return this.http.put<TaskItem>(`${this.url}/${id}`,data);}
 delete(id:number):Observable<void>{return this.http.delete<void>(`${this.url}/${id}`);}
}
