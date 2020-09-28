import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { Usuario } from '../models/usuario';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
    private currentUsuarioSubject: BehaviorSubject<Usuario>;
    public currentUsuario: Observable<Usuario>;

    constructor(private http: HttpClient) {
        this.currentUsuarioSubject = new BehaviorSubject<Usuario>(JSON.parse(localStorage.getItem('currentUsuario')));
        this.currentUsuario = this.currentUsuarioSubject.asObservable();
    }

    public get currentUsuarioValue(): Usuario {
        return this.currentUsuarioSubject.value;
    }

    login(Usuarioname, password) {
        return this.http.post<any>(`http://localhost:8080/api/blog/usuario/login`, { 'login': Usuarioname, 'senha': password })
            .pipe(map(Usuario => {
                localStorage.setItem('currentUsuario', JSON.stringify(Usuario));
                this.currentUsuarioSubject.next(Usuario);
                return Usuario;
            }));
    }

    logout() {
        // remove Usuario from local storage and set current Usuario to null
        localStorage.removeItem('currentUsuario');
        this.currentUsuarioSubject.next(null);
    }
}