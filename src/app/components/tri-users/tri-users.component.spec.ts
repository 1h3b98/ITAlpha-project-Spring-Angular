import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TriUsersComponent } from './tri-users.component';

describe('TriUsersComponent', () => {
  let component: TriUsersComponent;
  let fixture: ComponentFixture<TriUsersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TriUsersComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TriUsersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
