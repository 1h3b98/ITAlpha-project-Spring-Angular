import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InvMembersComponent } from './inv-members.component';

describe('InvMembersComponent', () => {
  let component: InvMembersComponent;
  let fixture: ComponentFixture<InvMembersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InvMembersComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InvMembersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
