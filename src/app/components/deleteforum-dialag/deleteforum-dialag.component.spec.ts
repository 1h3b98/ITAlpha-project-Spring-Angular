import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteforumDialagComponent } from './deleteforum-dialag.component';

describe('DeleteforumDialagComponent', () => {
  let component: DeleteforumDialagComponent;
  let fixture: ComponentFixture<DeleteforumDialagComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeleteforumDialagComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteforumDialagComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
