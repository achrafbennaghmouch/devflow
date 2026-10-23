export type ProjectStatus = 'PLANNED'|'IN_PROGRESS'|'COMPLETED'|'ON_HOLD';
export type TaskStatus = 'TODO'|'IN_PROGRESS'|'IN_REVIEW'|'DONE'|'BLOCKED';
export type TaskPriority = 'LOW'|'MEDIUM'|'HIGH'|'URGENT';
export interface Project {id:number;name:string;description:string|null;status:ProjectStatus;createdAt:string;updatedAt:string;}
export interface ProjectRequest {name:string;description:string;status:ProjectStatus;}
export interface TaskItem {id:number;title:string;description:string|null;status:TaskStatus;priority:TaskPriority;dueDate:string|null;projectId:number;projectName:string;createdAt:string;updatedAt:string;}
export interface TaskRequest {title:string;description:string;status:TaskStatus;priority:TaskPriority;dueDate:string|null;projectId:number;}
