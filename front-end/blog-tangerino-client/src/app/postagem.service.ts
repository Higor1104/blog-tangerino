import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PostagemService {
  private baseUrl = 'http://localhost:8080/api/blog/postagem';
  constructor(private http: HttpClient) { }

  createPostagem(user: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, user);
  }

  getPostagens(size: number, page: number): Observable<any> {
    return this.http.get('${this.baseUrl}');
  }

}
