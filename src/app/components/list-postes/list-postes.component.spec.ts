import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListPostesComponent } from './list-postes.component';

describe('ListPostesComponent', () => {
  let component: ListPostesComponent;
  let fixture: ComponentFixture<ListPostesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListPostesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListPostesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
