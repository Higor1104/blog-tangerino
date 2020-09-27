import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetalharPostagemComponent } from './detalhar-postagem.component';

describe('DetalharPostagemComponent', () => {
  let component: DetalharPostagemComponent;
  let fixture: ComponentFixture<DetalharPostagemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetalharPostagemComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetalharPostagemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
