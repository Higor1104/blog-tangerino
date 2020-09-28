import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Postagem } from '../models/postagem';

@Injectable({
  providedIn: 'root'
})
export class PostagemService {
  private baseUrl = 'http://localhost:8080/api/blog/postagem';
  constructor(private http: HttpClient) { }

  createPostagem(postagem: Postagem): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, postagem);
  }

  getPostagens(size: number, page: number): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

}
