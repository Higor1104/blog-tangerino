import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Usuario } from '../models/usuario';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  private baseUrl = 'http://localhost:8080/api/blog/usuario';

  constructor(private http: HttpClient) { }

  createUsuario(user: Usuario): Observable<Object> {
    return this.http.post(this.baseUrl, user);
  }

}
