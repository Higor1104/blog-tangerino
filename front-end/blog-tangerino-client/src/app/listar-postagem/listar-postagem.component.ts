import { PostagemService } from "../postagem.service";
import { Postagem } from "../postagem";
import { Observable } from "rxjs";
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-listar-postagem',
  templateUrl: './listar-postagem.component.html',
  styleUrls: ['./listar-postagem.component.css']
})
export class ListarPostagemComponent implements OnInit {

  postagens: Observable<Postagem[]>;

  constructor(private postagemService: PostagemService,
    private router: Router) { }

  ngOnInit(): void {
    this.reloadData();
  }

  reloadData() {
    this.postagens = this.postagemService.getPostagens(10, 0);
  }

  detalharPostagem(id: number){
    this.router.navigate(['detalharPostagem', id]);
  }

}
