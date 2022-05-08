import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TauxfComponent } from './tauxf.component';

describe('TauxfComponent', () => {
  let component: TauxfComponent;
  let fixture: ComponentFixture<TauxfComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TauxfComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TauxfComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
